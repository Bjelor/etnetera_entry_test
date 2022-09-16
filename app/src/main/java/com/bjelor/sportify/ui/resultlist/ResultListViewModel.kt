package com.bjelor.sportify.ui.resultlist

import androidx.lifecycle.ViewModel
import com.bjelor.sportify.domain.model.SportEntry
import com.bjelor.sportify.domain.usecase.GetSportEntriesUseCase
import kotlinx.coroutines.flow.Flow

class ResultListViewModel(
    getSportEntriesUseCase: GetSportEntriesUseCase,
) : ViewModel() {

    val results: Flow<List<SportEntry>> = getSportEntriesUseCase()

}
