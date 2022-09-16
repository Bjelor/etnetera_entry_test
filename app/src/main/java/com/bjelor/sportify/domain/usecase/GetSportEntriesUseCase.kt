package com.bjelor.sportify.domain.usecase

import com.bjelor.sportify.domain.SportEntryRepository
import com.bjelor.sportify.domain.model.SportEntry
import kotlinx.coroutines.flow.Flow

class GetSportEntriesUseCase(
    private val sportEntryRepository: SportEntryRepository,
) {

    operator fun invoke(): Flow<List<SportEntry>> =
        sportEntryRepository.getSportEntries(
            SportEntryRepository.Source.Both,
            SportEntryRepository.SortBy.Name
        )
}
