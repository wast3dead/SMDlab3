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
import androidx.compose.ui.unit.dp

@Composable
fun ParallaxSlowDemo(listState: LazyListState = rememberLazyListState()) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Part 3: Parallax Slow (State Read in Composition)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        // SLOW: Reading listState.firstVisibleItemScrollOffset directly during Composition
        val scrollOffset = listState.firstVisibleItemScrollOffset
        val parallaxOffset = (scrollOffset / 2).dp

        ParallaxHeaderSlow(parallaxOffset = parallaxOffset)

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
private fun ParallaxHeaderSlow(parallaxOffset: androidx.compose.ui.unit.Dp) {
    SideEffect {
        Log.d("Phases", "[ParallaxHeaderSlow] Composition phase")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .offset(y = parallaxOffset)
            .background(Color(0xFFFFB74D))
            .logPhases("ParallaxHeaderSlow"),
        contentAlignment = Alignment.Center
    ) {
        Text("Slow Parallax Header (Recomposes on Scroll)")
    }
}
