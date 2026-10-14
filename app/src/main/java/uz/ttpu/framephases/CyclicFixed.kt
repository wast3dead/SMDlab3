package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CyclicFixed() {
    SideEffect {
        Log.d("Phases", "[CyclicFixed] Composition phase")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Part 4: Cyclic Phase Fixed (Static Column)",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF2E7D32)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Layout size is determined naturally in a single measure/layout pass without writing back to Composition state!",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFC8E6C9))
                .padding(16.dp)
                .logPhases("CyclicFixedColumn"),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Child 1 inside static layout")
            Text("Child 2 inside static layout")
            Text("Single-pass measurement complete")
        }
    }
}
