package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ParentDemo() {
    var count by remember { mutableIntStateOf(0) }
    var deferStateRead by remember { mutableStateOf(false) }

    SideEffect {
        Log.d("Phases", "[Parent] Composition phase (count=$count)")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(2.dp, Color.Blue)
            .padding(16.dp)
    ) {
        Text(
            text = "Part 3: Parent-Child-Sibling Recomposition Scope",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { count++ }) {
            Text("Increment Counter: $count")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (deferStateRead) "Mode: Lambda Deferral (Child reads state in lambda)" else "Mode: Direct Value (Parent reads state)",
            style = MaterialTheme.typography.bodySmall
        )
        Switch(
            checked = deferStateRead,
            onCheckedChange = { deferStateRead = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (deferStateRead) {
            ChildLambda(countProvider = { count })
        } else {
            ChildDirect(count = count)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Sibling()
    }
}
