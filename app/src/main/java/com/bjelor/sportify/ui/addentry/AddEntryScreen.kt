package com.bjelor.sportify.ui.addentry

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bjelor.sportify.R
import com.bjelor.sportify.ui.addentry.durationpicker.DurationPickerDialog
import com.bjelor.sportify.ui.format
import com.bjelor.sportify.ui.theme.SportifyTheme
import java.time.LocalDate
import java.time.LocalTime
import org.koin.androidx.compose.getViewModel

@Composable
fun AddEntryScreen(onNavigateBack: () -> Unit) {
    val viewModel: AddEntryViewModel = getViewModel()

    val datePickerDialog = DatePicker(viewModel.date) { viewModel.date = it }
    val timePickerDialog = TimePicker(viewModel.time) { viewModel.time = it }

    val callbacks = AddEntryFormCallbacks(
        onDurationClick = { viewModel.isDurationPickerDialogVisible = true },
        onDateClick = { datePickerDialog.show() },
        onTimeClick = { timePickerDialog.show() },
        onSaveClick = {
            viewModel.onSaveClick()
            onNavigateBack.invoke()
        },
        onTargetSwitch = { /*TODO*/ },
        onNameChanged = { viewModel.name = it },
        onPlaceChanged = { viewModel.place = it },
    )

    AddEntryScreen(
        onNavigateBack = onNavigateBack,
        callbacks,
        viewModel.name,
        viewModel.duration.format(),
        viewModel.place,
        viewModel.date.format(),
        viewModel.time.format(),
    )

    if (viewModel.isDurationPickerDialogVisible) {
        DurationPickerDialog(
            value = viewModel.duration,
            onDismiss = { viewModel.isDurationPickerDialogVisible = false },
            onConfirmClick = { duration -> viewModel.duration = duration }
        )
    }
}

@Composable
private fun DatePicker(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit = {},
) = DatePickerDialog(
    LocalContext.current,
    { _, year, month, dayOfMonth ->
        onValueChange(LocalDate.of(year, month, dayOfMonth))
    },
    value.year,
    value.monthValue - 1,
    value.dayOfMonth,
)

@Composable
fun TimePicker(
    value: LocalTime,
    onValueChange: (LocalTime) -> Unit,
) = TimePickerDialog(
    LocalContext.current,
    { _, hour, minute -> onValueChange(LocalTime.of(hour, minute)) },
    value.hour,
    value.minute,
    true, // is24HourView
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddEntryScreen(
    onNavigateBack: () -> Unit,
    callbacks: AddEntryFormCallbacks,
    name: String,
    duration: String,
    place: String,
    date: String,
    time: String,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.addentry_topbar_title))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
        content = { paddingValues ->
            AddEntryForm(
                paddingValues,
                callbacks,
                name,
                duration,
                place,
                date,
                time,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddEntryForm(
    paddingValues: PaddingValues,
    callbacks: AddEntryFormCallbacks,
    name: String,
    duration: String,
    place: String,
    date: String,
    time: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp,
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = name,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = callbacks.onNameChanged,
            singleLine = true,
            label = { Text(stringResource(id = R.string.addentry_textfield_name_label)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        OutlinedTextField(
            value = place,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = callbacks.onPlaceChanged,
            label = { Text(stringResource(id = R.string.addentry_textfield_place_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        ReadOnlyOutlinedTextField(
            value = duration,
            modifier = Modifier.fillMaxWidth(),
            onActivate = callbacks.onDurationClick,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Timer,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(id = R.string.addentry_textfield_duration_label)) },
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ReadOnlyOutlinedTextField(
                value = date,
                modifier = Modifier.weight(1f),
                onActivate = callbacks.onDateClick,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.addentry_textfield_date_label)) },
            )
            ReadOnlyOutlinedTextField(
                value = time,
                modifier = Modifier.weight(1f),
                onActivate = callbacks.onTimeClick,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccessTime,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = R.string.addentry_textfield_time_label)) },
            )
        }

        RadioButton(selected = false, onClick = { /*TODO*/ })
        RadioButton(selected = true, onClick = { /*TODO*/ })

        Button(
            onClick = callbacks.onSaveClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = null)
            Text(text = stringResource(id = R.string.addentry_button_save))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReadOnlyOutlinedTextField(
    value: String,
    modifier: Modifier = Modifier,
    onActivate: () -> Unit,
    label: @Composable (() -> Unit),
    trailingIcon: @Composable (() -> Unit),
) {
    val source = remember { MutableInteractionSource() }

    if (source.collectIsPressedAsState().value) {
        onActivate.invoke()
    }

    OutlinedTextField(
        value = value,
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {
                    onActivate.invoke()
                }
            },
        onValueChange = { }, // handled in the pickers
        singleLine = true,
        trailingIcon = trailingIcon,
        label = label,
        readOnly = true,
        interactionSource = source,
    )
}


@Preview
@Composable
private fun AddEntryFormPreview() {
    SportifyTheme {
        AddEntryScreen(
            onNavigateBack = { },
            callbacks = AddEntryFormCallbacks({}, {}, {}, {}, {}, {}, {}),
            name = "name",
            duration = "10",
            place = "Brno",
            date = "",
            time = "",
        )
    }
}
