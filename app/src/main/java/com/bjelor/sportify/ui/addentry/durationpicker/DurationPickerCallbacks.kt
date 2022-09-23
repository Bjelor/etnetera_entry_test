package com.bjelor.sportify.ui.addentry.durationpicker

class DurationPickerCallbacks(
    val onDismiss: () -> Unit,
    val onConfirmClick: () -> Unit,
    val onBackSpaceClick: () -> Unit,
    val onDoubleZeroClick: () -> Unit,
    val onNumKeyClick: (Int) -> Unit,
)