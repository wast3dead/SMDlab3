package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Sibling() {
    SideEffect {
        Log.d("Phases", "[Sibling] Composition phase")
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFFFFECB3))
            .padding(8.dp)
    ) {
        Text(text = "Sibling (Does NOT read counter state)")
    }
}
