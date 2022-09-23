package com.bjelor.sportify.ui.addentry.durationpicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bjelor.sportify.R
import java.time.Duration
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DurationPickerDialog(
    value: Duration,
    onDismiss: () -> Unit,
    onConfirmClick: (Duration) -> Unit
) {
    val viewModel: DurationPickerViewModel = getViewModel { parametersOf(value) }

    val input: DurationPickerInput = viewModel.input
    val callbacks = DurationPickerCallbacks(
        onDismiss,
        { onConfirmClick(viewModel.result) },
        viewModel::onBackSpaceClick,
        viewModel::onDoubleZeroClick,
        viewModel::onNumKeyClick,
    )

    DurationPickerDialog(input, callbacks)
}

@Composable
private fun DurationPickerDialog(
    input: DurationPickerInput,
    callbacks: DurationPickerCallbacks,
) {
    Dialog(onDismissRequest = callbacks.onDismiss) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Row(
                        modifier = Modifier.weight(2f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CompositionLocalProvider(
                            LocalTextStyle provides LocalTextStyle.current.merge(
                                TextStyle(
                                    fontSize = 30.sp,
                                    lineHeight = 34.sp,
                                    letterSpacing = 0.sp
                                )
                            )
                        ) {
                            Digit(input.hours.left)
                            Digit(input.hours.right)
                            DigitDivider()
                            Digit(input.minutes.left)
                            Digit(input.minutes.right)
                            DigitDivider()
                            Digit(input.seconds.left)
                            Digit(input.seconds.right)
                        }
                    }

                    Key(
                        onClick = callbacks.onBackSpaceClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Backspace,
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                ) {
                    items((1..9).toList()) { key ->
                        Key(onClick = { callbacks.onNumKeyClick(key) }) {
                            Text(
                                text = key.toString(),
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Key(
                        onClick = { callbacks.onNumKeyClick(0) }
                    ) {
                        Text(
                            text = 0.toString(),
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                    Key(
                        onClick = callbacks.onDoubleZeroClick
                    ) {
                        Text(
                            text = "00",
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.secondary) {
                        Button(
                            onClick = callbacks.onDismiss,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.secondary,
                            )
                        ) {
                            Icon(imageVector = Icons.Filled.Cancel, contentDescription = null)
                            Text(
                                text = stringResource(
                                    id = R.string.addentry_durationpicker_button_cancel
                                )
                            )
                        }
                    }
                    Button(
                        onClick = {
                            callbacks.onConfirmClick()
                            callbacks.onDismiss()
                        },
                    ) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                        Text(
                            text = stringResource(
                                id = R.string.addentry_durationpicker_button_confirm
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Digit(value: Int?) {
    val textColor = if (value == null) {
        MaterialTheme.colorScheme.secondary
    } else {
        LocalTextStyle.current.color
    }

    Text(
        text = (value ?: 0).toString(),
        modifier = Modifier.width(IntrinsicSize.Min),
        color = textColor
    )
}

@Composable
private fun DigitDivider() {
    Text(
        text = ":",
        color = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
private fun RowScope.Key(
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Key(
        onClick = onClick,
        modifier = Modifier
            .weight(1f),
        content = content,
    )
}

@Composable
private fun Key(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick.invoke() },
        content = content,
    )
}

@Preview
@Composable
private fun DurationPickerDialogPreview() {
    DurationPickerDialog(
        input = DurationPickerInput(
            hours = DurationPickerInput.Digits(null, null),
            minutes = DurationPickerInput.Digits(null, 4),
            seconds = DurationPickerInput.Digits(3, 0)
        ),
        callbacks = DurationPickerCallbacks({}, {}, {}, {}, {}),
    )
}