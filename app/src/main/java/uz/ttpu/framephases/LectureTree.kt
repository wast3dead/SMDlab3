package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LectureTreeDemo() {
    SideEffect {
        Log.d("Phases", "[LectureTree] Composition phase")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .logPhases("LectureTreeRoot")
    ) {
        Text(
            text = "Part 2: Lecture Tree",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp))
                .padding(12.dp)
                .logPhases("Row"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image Box
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF6200EE))
                    .logPhases("ImageBox"),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "IMG", color = Color.White)
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Column with two Texts
            Column(
                modifier = Modifier
                    .weight(1f)
                    .logPhases("Column")
            ) {
                Text(
                    text = "First Title Text",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.logPhases("Text1")
                )
                Text(
                    text = "Second Subtitle Text",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.logPhases("Text2")
                )
            }
        }
    }
}
