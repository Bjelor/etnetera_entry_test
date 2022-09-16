package com.bjelor.sportify.domain.model

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

data class SportEntryForm(
    val name: String,
    val duration: Duration,
    val place: String,
    val date: LocalDate,
    val time: LocalTime,
)
