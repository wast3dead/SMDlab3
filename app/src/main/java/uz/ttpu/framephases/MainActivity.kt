package uz.ttpu.framephases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Part 1 & 2: Demos", "Part 3: Optimizations", "Part 4: Custom Layout")

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> Part1And2Screen()
                1 -> Part3Screen()
                2 -> Part4Screen()
            }
        }
    }
}

@Composable
fun Part1And2Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Part1Demo()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        LectureTreeDemo()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        PaddingDemo()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        OffsetDemo()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        ColorDemo()
    }
}

@Composable
fun Part3Screen() {
    var subTab by remember { mutableIntStateOf(0) }
    val subTabs = listOf("Parallax Slow", "Parallax Fast", "Scroll To Top", "Recomposition Scope")

    Column(modifier = Modifier.fillMaxSize()) {
        PrimaryTabRow(selectedTabIndex = subTab) {
            subTabs.forEachIndexed { index, title ->
                Tab(
                    selected = subTab == index,
                    onClick = { subTab = index },
                    text = { Text(title) }
                )
            }
        }

        when (subTab) {
            0 -> ParallaxSlowDemo()
            1 -> ParallaxFastDemo()
            2 -> ScrollToTopDemo()
            3 -> ParentDemo()
        }
    }
}

@Composable
fun Part4Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CyclicDemo()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        CyclicFixed()
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        StackBelowDemo()
    }
}
