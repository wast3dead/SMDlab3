package uz.ttpu.framephases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PaddingDemo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Part 2: Padding Modifier Order",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "1. Background BEFORE Padding:",
            style = MaterialTheme.typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.Red)
                .padding(16.dp)
                .background(Color.Yellow)
                .logPhases("PaddingBefore"),
            contentAlignment = Alignment.Center
        ) {
            Text("Inner Box")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "2. Padding BEFORE Background:",
            style = MaterialTheme.typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(120.dp)
                .background(Color.Green)
                .logPhases("PaddingAfter"),
            contentAlignment = Alignment.Center
        ) {
            Text("Inner Box")
        }
    }
}
