package com.bjelor.sportify.data.local

import com.bjelor.sportify.data.local.mapper.SportEntryFormLocalMapper
import com.bjelor.sportify.data.local.mapper.SportEntryLocalMapper
import com.bjelor.sportify.database.SportEntryDao
import com.bjelor.sportify.domain.model.SportEntry
import com.bjelor.sportify.domain.model.SportEntryForm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SportEntryLocalDataSource(
    private val sportEntryDao: SportEntryDao,
    private val sportEntryLocalMapper: SportEntryLocalMapper,
    private val sportEntryFormLocalMapper: SportEntryFormLocalMapper,
) {

    fun fetchSportEntries(): Flow<List<SportEntry>> = sportEntryDao.getAllSportEntries()
        .map { entries ->
            entries.map { sportEntryLocalMapper.map(it) }
        }

    suspend fun storeSportEntry(sportEntryForm: SportEntryForm) {
        val entity = sportEntryFormLocalMapper.map(sportEntryForm)

        sportEntryDao.insertOrUpdateSportEntry(entity)
    }
}
