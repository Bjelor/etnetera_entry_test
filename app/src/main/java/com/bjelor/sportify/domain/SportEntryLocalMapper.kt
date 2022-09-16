package com.bjelor.sportify.domain

import com.bjelor.sportify.database.SportEntryEntity
import java.time.Duration
import java.time.Instant

class SportEntryLocalMapper {

    fun map(entry: SportEntryEntity) = SportEntry(
        id = entry.id,
        name = entry.name,
        place = entry.place,
        duration = entry.duration.convertDuration(),
        time = entry.time.convertTime(),
        source = SportEntry.Source.Local,
    )

    private fun Int.convertDuration(): Duration =
        Duration.ofSeconds(this.toLong())

    private fun String.convertTime(): Instant =
        kotlin.runCatching {
            Instant.parse(this)
        }.getOrElse {
            Instant.now()
        }
}