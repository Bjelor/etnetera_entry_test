package com.bjelor.sportify

import androidx.lifecycle.ViewModel
import com.bjelor.sportify.domain.SportEntry
import com.bjelor.sportify.domain.usecase.GetSportEntriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ResultListViewModel(
    private val getSportEntriesUseCase: GetSportEntriesUseCase,
) : ViewModel() {

    val results: Flow<List<SportEntry>> = flow {
        emit(getSportEntriesUseCase())
    }

}
