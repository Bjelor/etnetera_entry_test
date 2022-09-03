package com.bjelor.sportify

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bjelor.sportify.ui.theme.SportifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportifyMainActivityContainer(
    content: @Composable (PaddingValues) -> Unit
) {
    SportifyTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    }
                )
            },
            content = content,
        )
    }
}
