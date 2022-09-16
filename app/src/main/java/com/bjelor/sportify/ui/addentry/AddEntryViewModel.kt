package com.bjelor.sportify.ui.addentry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bjelor.sportify.domain.model.SportEntryForm
import com.bjelor.sportify.domain.usecase.SaveLocalSportEntryUseCase
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import kotlinx.coroutines.launch

class AddEntryViewModel(
    private val saveLocalSportEntryUseCase: SaveLocalSportEntryUseCase,
) : ViewModel() {

    var name: String by mutableStateOf("")
    var duration: Int by mutableStateOf(0)
    var place: String by mutableStateOf("")
    var date: LocalDate by mutableStateOf(LocalDate.now())
    var time: LocalTime by mutableStateOf(LocalTime.now())

    private val parsedForm: SportEntryForm
        get() = SportEntryForm(
            name = name,
            duration = Duration.ofSeconds(duration * 60L), // TODO
            place = place,
            date = date,
            time = time,
        )

    fun onSaveClick() {
        viewModelScope.launch {
            saveLocalSportEntryUseCase(parsedForm)
        }
    }
}