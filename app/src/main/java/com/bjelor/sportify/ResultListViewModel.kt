package com.bjelor.sportify

import androidx.lifecycle.ViewModel
import com.bjelor.sportify.domain.SportEntry
import com.bjelor.sportify.domain.usecase.GetSportEntriesUseCase
import kotlinx.coroutines.flow.Flow

class ResultListViewModel(
    getSportEntriesUseCase: GetSportEntriesUseCase,
) : ViewModel() {

    val results: Flow<List<SportEntry>> = getSportEntriesUseCase()

}
