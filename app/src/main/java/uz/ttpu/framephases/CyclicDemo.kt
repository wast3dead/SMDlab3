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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun CyclicDemo() {
    var boxHeightPx by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current

    SideEffect {
        Log.d("Phases", "[CyclicDemo] Composition phase (boxHeightPx=$boxHeightPx)")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Part 4: Cyclic Phase Dependency (onSizeChanged -> State)",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Box height read via onSizeChanged: $boxHeightPx px. Reading layout size back into composition state causes cyclic phase updates!",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Cyclic phase dependency: Layout phase callback (onSizeChanged) modifies state read in Composition
                .onSizeChanged { size ->
                    Log.d("Phases", "[CyclicDemo] onSizeChanged callback: ${size.height}px")
                    boxHeightPx = size.height
                }
                .height(if (boxHeightPx == 0) 100.dp else with(density) { boxHeightPx.toDp() })
                .background(Color(0xFFFFCDD2))
                .logPhases("CyclicBox"),
            contentAlignment = Alignment.Center
        ) {
            Text("Cyclic Phase Dependency Box")
        }
    }
}
