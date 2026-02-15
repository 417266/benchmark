package com.example.benchmark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlin.random.Random

const val LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel augue quis sapien congue faucibus ac sed enim. Phasellus fermentum interdum arcu, id consectetur dui laoreet vitae."

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle =
                SystemBarStyle.light(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT,
                )
        )
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(containerColor = Color.White) { padding ->
                LazyColumn(contentPadding = padding) { items(1000) { ListItem(it) } }
            }
        }
    }
}

@Composable
fun ListItem(index: Int) {
    val text = remember { LOREM_IPSUM.slice(0..Random.nextInt(LOREM_IPSUM.lastIndex)) }
    val transition = rememberInfiniteTransition()
    val rotation by
        transition.animateFloat(
            0f,
            360f,
            infiniteRepeatable(tween(durationMillis = 1000, easing = LinearEasing)),
        )
    Row(
        Modifier.fillMaxWidth().padding(16.dp),
        Arrangement.spacedBy(16.dp),
        Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = "https://placehold.co/256x256.png?text=${index + 1}",
            contentDescription = null,
            modifier = Modifier.size(48.dp),
        )
        Text(text, Modifier.weight(1f))
        Box(Modifier.graphicsLayer { rotationZ = rotation }.background(Color.Black).size(24.dp))
    }
}
