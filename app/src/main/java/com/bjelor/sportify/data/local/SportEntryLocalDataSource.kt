package com.bjelor.sportify.data.local

import com.bjelor.sportify.domain.SportEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

class SportEntryLocalDataSource {

    // TODO
    suspend fun fetchSportEntries(): List<SportEntry> = withContext(Dispatchers.Default) {
        listOf(
            SportEntry("dummy1", Duration.of(2, ChronoUnit.HOURS), "Brno", Instant.now()),
            SportEntry(
                "dummy2",
                Duration.of(5, ChronoUnit.MINUTES),
                "Prague",
                Instant.now().minusSeconds(3600)
            ),
            SportEntry(
                "dummy3",
                Duration.of(6, ChronoUnit.MINUTES),
                "Prague",
                Instant.now().minusSeconds(3600 * 2)
            ),
            SportEntry(
                "dummy4",
                Duration.of(7, ChronoUnit.MINUTES),
                "Prague",
                Instant.now().minusSeconds(3600 * 3)
            ),
            SportEntry(
                "dummy5",
                Duration.of(8, ChronoUnit.MINUTES),
                "Prague",
                Instant.now().minusSeconds(3600 * 4)
            ),
            SportEntry(
                "dummy6",
                Duration.of(9, ChronoUnit.MINUTES),
                "Prague",
                Instant.now().minusSeconds(3600 * 5)
            ),
        )
    }
}
