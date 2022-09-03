package com.bjelor.sportify.data.remote

import com.bjelor.sportify.domain.SportEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

class SportEntryRemoteDataSource {

    // TODO
    suspend fun fetchSportEntries(): List<SportEntry> = withContext(Dispatchers.Default) {
        listOf(
            SportEntry(
                "dummy1",
                Duration.of(2, ChronoUnit.HOURS),
                "Brno",
                Instant.now(),
                SportEntry.Source.Remote,
            ),
            SportEntry(
                "dummy2",
                Duration.of(5, ChronoUnit.MINUTES),
                "Prague",
                Instant.now().minusSeconds(3600),
                SportEntry.Source.Remote,
            ),
            SportEntry(
                "dummy3",
                Duration.of(6, ChronoUnit.MINUTES),
                "Prague",
                Instant.now().minusSeconds(3600 * 2),
                SportEntry.Source.Remote,
            )
        )
    }
}
