package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun ParallaxFastDemo(listState: LazyListState = rememberLazyListState()) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Part 3: Parallax Fast (Deferred State Read)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        // FAST: Passing scroll offset lambda to defer state read to Layout phase
        ParallaxHeaderFast(scrollOffsetProvider = { listState.firstVisibleItemScrollOffset })

        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            items(30) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Item #$index",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ParallaxHeaderFast(scrollOffsetProvider: () -> Int) {
    SideEffect {
        Log.d("Phases", "[ParallaxHeaderFast] Composition phase")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .offset { IntOffset(0, scrollOffsetProvider() / 2) }
            .background(Color(0xFF81C784))
            .logPhases("ParallaxHeaderFast"),
        contentAlignment = Alignment.Center
    ) {
        Text("Fast Parallax Header (Skips Recomposition!)")
    }
}
