package uz.ttpu.framephases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Custom layout implementing a 3-step layout algorithm:
 * 1. Measure children with loose constraints.
 * 2. Calculate layout's own size.
 * 3. Place children below each other with spacing.
 */
@Composable
fun StackBelow(
    modifier: Modifier = Modifier,
    spacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Step 1: Measure children with loose constraints
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val placeables = measurables.map { measurable ->
            measurable.measure(looseConstraints)
        }

        val spacingPx = spacing.roundToPx()

        // Step 2: Calculate own size
        val layoutWidth = placeables.maxOfOrNull { it.width }?.coerceIn(constraints.minWidth, constraints.maxWidth)
            ?: constraints.minWidth
        val totalChildrenHeight = placeables.sumOf { it.height }
        val totalSpacingHeight = if (placeables.size > 1) (placeables.size - 1) * spacingPx else 0
        val layoutHeight = (totalChildrenHeight + totalSpacingHeight).coerceIn(constraints.minHeight, constraints.maxHeight)

        // Step 3: Place children below each other
        layout(layoutWidth, layoutHeight) {
            var yPosition = 0
            for (placeable in placeables) {
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height + spacingPx
            }
        }
    }
}

@Composable
fun StackBelowDemo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Part 4: Custom StackBelow Layout",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        StackBelow(
            spacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDE7F6))
                .padding(12.dp)
                .logPhases("StackBelowLayout")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color(0xFFB39DDB))
                    .logPhases("StackItem1"),
                contentAlignment = Alignment.Center
            ) {
                Text("Stack Child 1 (Height 40dp)")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFF9575CD))
                    .logPhases("StackItem2"),
                contentAlignment = Alignment.Center
            ) {
                Text("Stack Child 2 (Height 60dp)", color = Color.White)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(Color(0xFF7E57C2))
                    .logPhases("StackItem3"),
                contentAlignment = Alignment.Center
            ) {
                Text("Stack Child 3 (Height 30dp)", color = Color.White)
            }
        }
    }
}
