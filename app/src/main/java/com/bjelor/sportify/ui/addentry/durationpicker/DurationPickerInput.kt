package com.bjelor.sportify.ui.addentry.durationpicker

class DurationPickerInput(
    val hours: Digits = Digits(),
    val minutes: Digits = Digits(),
    val seconds: Digits = Digits(),
) {
    class Digits(
        val left: Int? = null,
        val right: Int? = null,
    )
}