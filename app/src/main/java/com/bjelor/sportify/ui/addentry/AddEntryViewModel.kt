package com.bjelor.sportify.ui.addentry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bjelor.sportify.domain.SportEntryRepository
import com.bjelor.sportify.domain.model.SportEntryForm
import com.bjelor.sportify.domain.usecase.SaveLocalSportEntryUseCase
import com.bjelor.sportify.domain.usecase.SaveRemoteSportEntryUseCase
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import kotlinx.coroutines.launch

class AddEntryViewModel(
    private val saveLocalSportEntryUseCase: SaveLocalSportEntryUseCase,
    private val saveRemoteSportEntryUseCase: SaveRemoteSportEntryUseCase,
) : ViewModel() {

    var name: String by mutableStateOf("")
    var duration: Duration by mutableStateOf(Duration.ofSeconds(0))
    var place: String by mutableStateOf("")
    var date: LocalDate by mutableStateOf(LocalDate.now())
    var time: LocalTime by mutableStateOf(LocalTime.now())

    var target: SportEntryRepository.Target by mutableStateOf(SportEntryRepository.Target.Local)

    var isDurationPickerDialogVisible: Boolean by mutableStateOf(false)

    private val parsedForm: SportEntryForm
        get() = SportEntryForm(
            name = name,
            duration = duration,
            place = place,
            date = date,
            time = time,
        )

    fun onSaveClick() {
        viewModelScope.launch {
            when (target) {
                SportEntryRepository.Target.Local -> saveLocalSportEntryUseCase(parsedForm)
                SportEntryRepository.Target.Remote -> saveRemoteSportEntryUseCase(parsedForm)
            }
        }
    }
}