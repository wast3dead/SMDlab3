package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorDemo() {
    var isRed by remember { mutableStateOf(true) }
    val currentColor = if (isRed) Color.Red else Color.Blue

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Part 2: Color State Read - Canvas Draw vs Background Modifier",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(onClick = { isRed = !isRed }) {
            Text("Toggle Color")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("1. Background Modifier")
                BackgroundModifierBox(color = currentColor)
            }

            Spacer(modifier = Modifier.width(32.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("2. Canvas / Draw Lambda")
                CanvasDrawBox(colorProvider = { currentColor })
            }
        }
    }
}

@Composable
private fun BackgroundModifierBox(color: Color) {
    SideEffect {
        Log.d("Phases", "[BackgroundModifierBox] Composition phase")
    }

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(color)
            .logPhases("BackgroundModifier"),
        contentAlignment = Alignment.Center
    ) {
        Text("Modifier", color = Color.White)
    }
}

@Composable
private fun CanvasDrawBox(colorProvider: () -> Color) {
    SideEffect {
        Log.d("Phases", "[CanvasDrawBox] Composition phase")
    }

    Box(
        modifier = Modifier
            .size(80.dp)
            .drawBehind {
                Log.d("Phases", "[CanvasDrawBox] Draw phase (lambda read)")
                drawRect(colorProvider())
            }
            .logPhases("CanvasDraw"),
        contentAlignment = Alignment.Center
    ) {
        Text("Canvas", color = Color.White)
    }
}
