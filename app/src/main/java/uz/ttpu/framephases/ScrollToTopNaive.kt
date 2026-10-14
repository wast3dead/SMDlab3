package uz.ttpu.framephases

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopDemo() {
    var useDerivedState by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Naive state read: re-evaluates and triggers recomposition on scroll changes
    val showButtonNaive = listState.firstVisibleItemIndex > 0

    // Optimized state read: derivedStateOf re-evaluates but only triggers recomposition when boolean changes
    val showButtonDerived by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    val showButton = if (useDerivedState) showButtonDerived else showButtonNaive

    SideEffect {
        Log.d("Phases", "[ScrollToTopDemo] Composition phase (useDerivedState=$useDerivedState, showButton=$showButton)")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Part 3: Scroll To Top Optimization",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = if (useDerivedState) "Using derivedStateOf (Optimized)" else "Naive State Read (Recomposes on scroll)",
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Switch(
                    checked = useDerivedState,
                    onCheckedChange = { useDerivedState = it },
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f)
            ) {
                items(50) { index ->
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

        AnimatedVisibility(
            visible = showButton,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ) {
                Text("Scroll to Top")
            }
        }
    }
}
