package com.bjelor.sportify.data.local

import com.bjelor.sportify.database.SportEntryDao
import com.bjelor.sportify.domain.SportEntry
import com.bjelor.sportify.domain.SportEntryLocalMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SportEntryLocalDataSource(
    private val sportEntryDao: SportEntryDao,
    private val sportEntryLocalMapper: SportEntryLocalMapper,
) {

    fun fetchSportEntries(): Flow<List<SportEntry>> = sportEntryDao.getAllSportEntries()
        .map { entries ->
            entries.map { sportEntryLocalMapper.map(it) }
        }
}
