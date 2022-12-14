package com.bjelor.sportify.domain.model

import java.time.Duration
import java.time.Instant

data class SportEntry(
    val name: String,
    val duration: Duration,
    val place: String,
    val time: Instant,
    val source: Source = Source.Local, // FIXME
    val id: Int = 0, // FIXME
) {

    enum class Source {
        Local,
        Remote,
    }
}
