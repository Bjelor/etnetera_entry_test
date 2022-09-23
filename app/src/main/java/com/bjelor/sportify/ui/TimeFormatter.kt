package com.bjelor.sportify.ui

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

enum class DateTimeStyle {
    Long,
    Short,
}

fun LocalDate.format(dateTimeStyle: DateTimeStyle = DateTimeStyle.Short): String =
    when (dateTimeStyle) {
        DateTimeStyle.Long -> FormatStyle.MEDIUM
        DateTimeStyle.Short -> FormatStyle.SHORT
    }.let { formatStyle ->
        DateTimeFormatter.ofLocalizedDate(formatStyle).format(this)
    }

fun LocalTime.format(dateTimeStyle: DateTimeStyle = DateTimeStyle.Short): String =
    when (dateTimeStyle) {
        DateTimeStyle.Long -> FormatStyle.MEDIUM
        DateTimeStyle.Short -> FormatStyle.SHORT
    }.let { formatStyle ->
        DateTimeFormatter.ofLocalizedTime(formatStyle).format(this)
    }

fun Duration.format(): String {
    val hours = this.toHours()
    val minutes = this.minusHours(hours).toMinutes()
    val seconds = this.minusHours(hours).minusMinutes(minutes).toSeconds()

    return String.format("%1\$02d:%2\$02d:%3\$02d", hours, minutes, seconds)
}
