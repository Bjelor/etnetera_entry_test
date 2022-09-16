package com.bjelor.sportify.ui.addentry

class AddEntryFormCallbacks(
    val onDateClick: () -> Unit,
    val onTimeClick: () -> Unit,
    val onSaveClick: () -> Unit,
    val onTargetSwitch: (Boolean) -> Unit,
    val onNameChanged: (String) -> Unit,
    val onDurationChanged: (String) -> Unit,
    val onPlaceChanged: (String) -> Unit,
)
