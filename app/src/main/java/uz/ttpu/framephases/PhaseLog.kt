package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

/**
 * Modifier helper to log Layout (Measure & Place) and Draw phases using tag "Phases".
 */
fun Modifier.logPhases(name: String): Modifier = this
    .layout { measurable, constraints ->
        Log.d("Phases", "[$name] Measure phase")
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            Log.d("Phases", "[$name] Place phase")
            placeable.placeRelative(0, 0)
        }
    }
    .drawWithContent {
        Log.d("Phases", "[$name] Draw phase")
        drawContent()
    }

@Composable
fun Part1Demo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Part 1: Phase Logging Helper",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Modifier.logPhases(name) logs Layout (measure/place) and Draw phases to Logcat using tag 'Phases'.",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFFBBDEFB))
                .logPhases("Part1Box"),
            contentAlignment = Alignment.Center
        ) {
            Text("Inspected by Modifier.logPhases(\"Part1Box\")")
        }
    }
}
