package com.bjelor.sportify.ui.addentry.durationpicker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.Duration

class DurationPickerViewModel(presetDuration: Duration?) : ViewModel() {

    /**
     * List of digits as entered by the user which
     * will be used to format the duration input.
     * E.g. listOf(1, 4, 0, 3, 0) corresponds to 01:40:30.
     */
    private val digits: MutableList<Int>

    var input: DurationPickerInput by mutableStateOf(DurationPickerInput())
        private set

    val result: Duration
        get() {
            val hours = (input.hours.left ?: 0) * 10L + (input.hours.right ?: 0)
            val minutes = (input.minutes.left ?: 0) * 10L + (input.minutes.right ?: 0)
            val seconds = (input.seconds.left ?: 0) * 10L + (input.seconds.right ?: 0)

            return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds)
        }

    init {
        digits = initializeDigits(presetDuration)
        updateDurationPickerInput(digits)
    }

    fun onNumKeyClick(key: Int) {
        addDigit(key)
    }

    fun onDoubleZeroClick() {
        clearDigits()
    }

    fun onBackSpaceClick() {
        popDigit()
    }

    private fun addDigit(digit: Int) {
        val validDigit = digit.validateDigit()

        if (digits.isEmpty() && validDigit == 0) {
            return
        }

        if (digits.size <= 6) {
            digits.add(validDigit)
        }
        updateDurationPickerInput(digits)
    }

    private fun popDigit() {
        if (digits.isNotEmpty()) {
            digits.removeLast()
        }
        updateDurationPickerInput(digits)
    }

    private fun clearDigits() {
        digits.clear()
        updateDurationPickerInput(digits)
    }

    /**
     * Takes a list of digits where the item with index 0 is the leftmost digit.
     */
    private fun updateDurationPickerInput(ltrDigits: List<Int>) {
        // Reverse the list of digits so that the item
        // with index 0 is always the rightmost seconds digit.
        ltrDigits.reversed().let { digits ->
            input = DurationPickerInput(
                hours = DurationPickerInput.Digits(
                    left = digits.getOrNull(5),
                    right = digits.getOrNull(4),
                ),
                minutes = DurationPickerInput.Digits(
                    left = digits.getOrNull(3),
                    right = digits.getOrNull(2),
                ),
                seconds = DurationPickerInput.Digits(
                    left = digits.getOrNull(1),
                    right = digits.getOrNull(0),
                ),
            )
        }
    }

    private fun initializeDigits(presetDuration: Duration?): MutableList<Int> {
        if (presetDuration == null)
            return mutableListOf()

        val hours = presetDuration.toHours()
        val minutes = presetDuration.minusHours(hours).toMinutes()
        val seconds = presetDuration.minusHours(hours).minusMinutes(minutes).toSeconds()

        return mutableListOf<Int>()
            .apply {
                fun addIfNotGuidingZero(element: Int) {
                    if (element > 0 || isNotEmpty()) {
                        add(element)
                    }
                }

                addIfNotGuidingZero(hours.leftDigit)
                addIfNotGuidingZero(hours.rightDigit)

                addIfNotGuidingZero(minutes.leftDigit)
                addIfNotGuidingZero(minutes.rightDigit)

                addIfNotGuidingZero(seconds.leftDigit)
                addIfNotGuidingZero(seconds.rightDigit)
            }
    }

    private val Long.leftDigit: Int
        get() = (this / 10).toInt().validateDigit()

    private val Long.rightDigit: Int
        get() = (this % 10).toInt().validateDigit()

    private fun Int.validateDigit() = coerceIn(0..9)

}