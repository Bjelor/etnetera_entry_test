package com.bjelor.sportify.domain

import com.bjelor.sportify.data.local.SportEntryLocalDataSource
import com.bjelor.sportify.data.remote.SportEntryRemoteDataSource

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

    suspend fun getSportEntries(source: Source, sortBy: SortBy = SortBy.Time): List<SportEntry> =
        fetchSportEntriesFromSource(source)
            .sortedBy(sortBy)

    // TODO
    fun storeSportEntry(entry: SportEntry, target: Target) {
        when (target) {
            Target.Remote -> TODO()
            Target.Local -> TODO()
        }
    }

    private suspend fun fetchSportEntriesFromSource(source: Source): List<SportEntry> =
        when (source) {
            Source.Remote -> remoteDataSource.fetchSportEntries()
            Source.Local -> localDataSource.fetchSportEntries()
            Source.Both -> {
                (remoteDataSource.fetchSportEntries() + localDataSource.fetchSportEntries())
            }
        }

    private fun List<SportEntry>.sortedBy(sortBy: SortBy) = when (sortBy) {
        SortBy.Time -> sortedBy { it.time }
        SortBy.Name -> sortedBy { it.name }
    }
}
