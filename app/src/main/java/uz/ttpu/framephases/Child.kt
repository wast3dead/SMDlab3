package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun ChildDirect(count: Int) {
    SideEffect {
        Log.d("Phases", "[ChildDirect] Composition phase (count=$count)")
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFFE3F2FD))
            .padding(8.dp)
    ) {
        Text(text = "Child Direct Value: $count")
    }
}

@Composable
fun ChildLambda(countProvider: () -> Int) {
    SideEffect {
        Log.d("Phases", "[ChildLambda] Composition phase")
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .offset { IntOffset(countProvider() * 5, 0) }
            .size(120.dp, 40.dp)
            .background(Color(0xFFC8E6C9))
            .logPhases("ChildLambda"),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Child Lambda")
    }
}
