package com.example.calculator.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.viewmodel.CalculatorViewModel


@Composable
fun CalculatorScreen(
    viewModel1: CalculatorViewModel,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current
    val backgroundBrush = if (isDarkMode) {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xA4494D4D),
                Color(0x792C2D2F),
                Color(0xFF080909)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primary)
        )
    }

    val state by viewModel1.state.collectAsState()
    val history by viewModel1.history.collectAsState()
    var showHistory by rememberSaveable { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(brush = backgroundBrush)
                .padding(bottom = 50.dp)
                .fillMaxWidth()
        ) {
            val expressionFontSize = when {
                state.expression.length > 40 -> 29.sp
                state.expression.length > 34 -> 24.sp
                state.expression.length > 28 -> 30.sp
                state.expression.length > 16 -> 32.sp
                else -> 36.sp // 👈 default font size for short expressions
            }

            // Expression + Result
            val scrollState = rememberScrollState()
            LaunchedEffect(state.expression) {
                scrollState.animateScrollTo(scrollState.maxValue)
            }



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(174.dp)
                    .padding(end = 16.dp, top = 76.dp, start = 99.dp, bottom = 28.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = buildAnnotatedString {
                        val operators = listOf('+', '-', 'x', '÷', '*', '/', '%', '^')

                        state.expression.forEach { char ->
                            if (char in operators) {
                                withStyle(
                                    style = SpanStyle(color = Color(0xFFA965E1)) // 🎯 CHANGE OPERATOR COLOR HERE
                                ) {
                                    append(char)
                                }
                            } else {
                                append(char)
                            }
                        }
                    },
                    style = TextStyle(fontSize = expressionFontSize),
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surfaceContainerLow // color for numbers
                )
            }


            Text(
                text = "= ${state.result}",
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )

            // Top Row Icons: History + Theme toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 19.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = if (showHistory) Icons.Filled.Calculate else Icons.Default.History,
                    contentDescription = "Toggle History",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .clickable {
                            performHapticAndSound(context, view)
                            showHistory = !showHistory
                        },
                    tint = MaterialTheme.colorScheme.inversePrimary
                )

                Icon(
                    imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Default.DarkMode, // use real theme icon here
                    contentDescription = "Toggle Theme",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(35.dp)

                        .clip(CircleShape)
                        .clickable {
                            performHapticAndSound(context, view)
                            onToggleTheme()
                        },
                    tint = MaterialTheme.colorScheme.inversePrimary
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.inverseSurface
            )

            ButtonPad(viewModel1)
        }
    }

    if (showHistory) {
        History(history = history, isDarkMode = isDarkMode)
    }
}







