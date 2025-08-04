package com.example.calculator.view

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun History(history: List<Pair<String, String>>, isDarkMode: Boolean) {
    val isDark = isDarkMode

    val backgroundBrush = if (isDark) {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0.3f to Color(0xED2C2D2F),
                0.5f to Color(0xC82C2D2F),
                1.0f to Color(0xFF080909)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(Color.White, Color.White)
        )
    }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        Row {
            LazyColumn(
                modifier = Modifier
                    .width(304.dp)
                    .height(561.dp)
                    .background(brush =  backgroundBrush), // ✅ now works correctly
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                items(history.size) { index ->
                    val (expression, result) = history[index]
                    Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Column(
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = expression,
                                color = Color.Black, // optionally make theme-aware
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "= $result",
                                color = Color(0xFF9228E2)
                            )
                        }
                    }
                }

                item {
                    Box(modifier = Modifier.height(54.dp))
                }
            }

            Divider(
                modifier = Modifier
                    .heightIn(503.dp)
                    .padding(top = 2.dp)
                    .width(2.dp),
                color = MaterialTheme.colorScheme.errorContainer
            )
        }

        // Fade on top to blend
        Box(
            modifier = Modifier
                .padding(bottom = 216.dp, end = 110.dp)
                .width(304.dp)
                .height(15.dp)
                .background(
                    Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(Color.Transparent, Color.Transparent)
                        } else {
                            listOf(
                                Color.White,
                                Color.White.copy(alpha = 0.7f),
                                Color.White.copy(alpha = 0.4f)
                            )
                        }
                    )
                )
                .align(Alignment.Center)
        )

        // Bottom Fade
        Box(
            modifier = Modifier
                .padding(end = 110.dp)
                .width(304.dp)
                .height(50.dp)
                .background(
                    Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color(0xFF080909).copy(alpha = 0.4f),
                                Color(0xFF080909).copy(alpha = 0.7f),
                                Color(0xFF080909),
                                Color(0xFF080909)
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.4f),
                                Color.White.copy(alpha = 0.7f),
                                Color.White,
                                Color.White
                            )
                        }
                    )
                )
                .align(Alignment.BottomStart)
        )
    }
}
