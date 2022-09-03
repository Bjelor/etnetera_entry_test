package com.bjelor.sportify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bjelor.sportify.domain.SportEntry
import com.bjelor.sportify.ui.theme.SportifyTheme
import org.koin.androidx.compose.getViewModel
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

@Composable
fun ResultListScreen(paddingValues: PaddingValues) {
    val viewModel: ResultListViewModel = getViewModel()

    val results by viewModel.results.collectAsState(initial = emptyList())

    ResultList(paddingValues, results)
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
            Text(text = item.duration.toString())
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
        ResultList(
            paddingValues = PaddingValues(),
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
