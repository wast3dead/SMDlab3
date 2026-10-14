package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun OffsetDemo() {
    var offsetValue by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Part 2: Offset Lambda Experiment",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(text = "Offset: ${offsetValue.roundToInt()} px")
        Slider(
            value = offsetValue,
            onValueChange = { offsetValue = it },
            valueRange = 0f..100f
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "1. Non-lambda Offset (Recomposes on value change):",
            style = MaterialTheme.typography.bodyMedium
        )
        NonLambdaOffsetBox(offsetPx = offsetValue.dp)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "2. Lambda Offset (Defers state read to Layout phase, skips Recomposition):",
            style = MaterialTheme.typography.bodyMedium
        )
        LambdaOffsetBox(offsetPxProvider = { offsetValue.roundToInt() })
    }
}

@Composable
private fun NonLambdaOffsetBox(offsetPx: androidx.compose.ui.unit.Dp) {
    SideEffect {
        Log.d("Phases", "[NonLambdaOffsetBox] Composition phase")
    }

    Box(
        modifier = Modifier
            .offset(x = offsetPx)
            .size(60.dp)
            .background(Color.LightGray)
            .logPhases("NonLambdaOffset"),
        contentAlignment = Alignment.Center
    ) {
        Text("Direct")
    }
}

@Composable
private fun LambdaOffsetBox(offsetPxProvider: () -> Int) {
    SideEffect {
        Log.d("Phases", "[LambdaOffsetBox] Composition phase")
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(x = offsetPxProvider(), y = 0) }
            .size(60.dp)
            .background(Color.Cyan)
            .logPhases("LambdaOffset"),
        contentAlignment = Alignment.Center
    ) {
        Text("Lambda")
    }
}
