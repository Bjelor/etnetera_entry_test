package com.bjelor.sportify.domain

import com.bjelor.sportify.data.local.SportEntryLocalDataSource
import com.bjelor.sportify.data.remote.SportEntryRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class SportEntryRepository(
    private val localDataSource: SportEntryLocalDataSource,
    private val remoteDataSource: SportEntryRemoteDataSource,
) {

    enum class Source {
        Remote,
        Local,
        Both
    }

    enum class Target {
        Remote,
        Local,
    }

    enum class SortBy {
        Time,
        Name,
    }

    fun getSportEntries(source: Source, sortBy: SortBy = SortBy.Time): Flow<List<SportEntry>> =
        fetchSportEntriesFromSource(source)
            .sortedBy(sortBy)

    // TODO
    fun storeSportEntry(entry: SportEntry, target: Target) {
        when (target) {
            Target.Remote -> TODO()
            Target.Local -> TODO()
        }
    }

    private fun fetchSportEntriesFromSource(source: Source): Flow<List<SportEntry>> =
        when (source) {
            Source.Remote -> remoteDataSource.fetchSportEntries()
            Source.Local -> localDataSource.fetchSportEntries()
            Source.Both -> {
                combine(
                    remoteDataSource.fetchSportEntries(),
                    localDataSource.fetchSportEntries()
                ) { remoteEntries, localEntries ->
                    remoteEntries + localEntries
                }
            }
        }

    private fun Flow<List<SportEntry>>.sortedBy(sortBy: SortBy) = when (sortBy) {
        SortBy.Time -> map { list -> list.sortedBy { it.time } }
        SortBy.Name -> map { list -> list.sortedBy { it.name } }
    }
}
