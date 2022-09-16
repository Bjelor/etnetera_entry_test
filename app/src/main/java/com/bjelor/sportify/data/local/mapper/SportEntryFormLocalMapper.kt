package com.bjelor.sportify.data.local.mapper

import com.bjelor.sportify.database.SportEntryEntity
import com.bjelor.sportify.domain.model.SportEntryForm
import java.time.Duration
import java.time.ZoneOffset

class SportEntryFormLocalMapper {

    fun map(entry: SportEntryForm) = SportEntryEntity(
        name = entry.name,
        place = entry.place,
        duration = entry.duration.convertDuration(),
        time = entry.convertTime(),
    )

    private fun Duration.convertDuration(): Int = seconds.toInt()

    private fun SportEntryForm.convertTime(): String =
        date.atTime(time).toInstant(ZoneOffset.UTC).toString()

}