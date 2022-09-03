package com.bjelor.sportify.domain.usecase

import com.bjelor.sportify.domain.SportEntry
import com.bjelor.sportify.domain.SportEntryRepository

class GetSportEntriesUseCase(
    private val sportEntryRepository: SportEntryRepository,
) {

    suspend operator fun invoke(): List<SportEntry> =
        sportEntryRepository.getSportEntries(
            SportEntryRepository.Source.Both,
            SportEntryRepository.SortBy.Name
        )
}
