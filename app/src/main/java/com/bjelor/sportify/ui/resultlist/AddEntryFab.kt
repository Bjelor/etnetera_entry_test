package com.bjelor.sportify.ui.resultlist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddEntryFab(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Preview
@Composable
private fun AddEntryFabPreview() {
    AddEntryFab { }
}