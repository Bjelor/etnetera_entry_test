package com.bjelor.sportify.domain.usecase

import com.bjelor.sportify.domain.SportEntryRepository
import com.bjelor.sportify.domain.model.SportEntryForm

class SaveRemoteSportEntryUseCase(
    private val sportEntryRepository: SportEntryRepository,
) {

    suspend operator fun invoke(sportEntryForm: SportEntryForm) {
        sportEntryRepository.storeSportEntry(sportEntryForm, SportEntryRepository.Target.Remote)
    }
}
