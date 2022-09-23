package com.bjelor.sportify.ui.resultlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bjelor.sportify.R
import com.bjelor.sportify.domain.model.SportEntry
import com.bjelor.sportify.ui.format
import com.bjelor.sportify.ui.theme.SportifyTheme
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit
import org.koin.androidx.compose.getViewModel

@Composable
fun ResultListScreen(
    onNavigateToAddEntry: () -> Unit,
) {
    val viewModel: ResultListViewModel = getViewModel()

    val results by viewModel.results.collectAsState(initial = emptyList())

    ResultListScreen(onNavigateToAddEntry, results)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ResultListScreen(
    onNavigateToAddEntry: () -> Unit,
    results: List<SportEntry>,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.FilterList, null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.SwapVert, null)
                    }
                }
            )
        },
        content = { paddingValues ->
            ResultList(paddingValues, results)
        },
        floatingActionButton = {
            AddEntryFab(onClick = onNavigateToAddEntry)
        }
    )
}

@Composable
fun ResultList(paddingValues: PaddingValues, results: List<SportEntry>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        items(
            items = results
        ) { item ->
            ResultListItem(item)
        }
    }
}

@Composable
fun ResultListItem(item: SportEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .wrapContentSize(Alignment.CenterStart)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(16.dp)
                .background(item.resolveColor())
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterVertically),
        ) {
            Text(text = item.name)
            Text(text = item.duration.format())
        }
    }
}

private fun SportEntry.resolveColor(): Color =
    when (source) {
        SportEntry.Source.Local -> Color.Cyan
        SportEntry.Source.Remote -> Color.Magenta
    }

@Preview
@Composable
fun ResultListPreview() {
    SportifyTheme {
        ResultListScreen(
            onNavigateToAddEntry = { },
            results = listOf(
                SportEntry("dummy1", Duration.of(2, ChronoUnit.HOURS), "Brno", Instant.now()),
                SportEntry(
                    "dummy2",
                    Duration.of(5, ChronoUnit.MINUTES),
                    "Prague",
                    Instant.now().minusSeconds(3600)
                ),
                SportEntry(
                    "dummy3",
                    Duration.of(5, ChronoUnit.MINUTES),
                    "Prague",
                    Instant.now().minusSeconds(3600)
                ),
                SportEntry(
                    "dummy4",
                    Duration.of(5, ChronoUnit.MINUTES),
                    "Prague",
                    Instant.now().minusSeconds(3600)
                ),
                SportEntry(
                    "dummy2",
                    Duration.of(5, ChronoUnit.MINUTES),
                    "Prague",
                    Instant.now().minusSeconds(3600)
                ),
                SportEntry(
                    "dummy2",
                    Duration.of(5, ChronoUnit.MINUTES),
                    "Prague",
                    Instant.now().minusSeconds(3600)
                ),
            )
        )
    }
}
