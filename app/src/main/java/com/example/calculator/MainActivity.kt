package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.view.CalculatorScreen
import com.example.calculator.viewmodel.CalculatorViewModel

// MainActivity.kt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf(false) } // Theme toggle state

            CalculatorTheme(darkTheme = isDarkMode) {
                CalculatorApp(
                    isDarkMode = isDarkMode,
                    onToggleTheme = { isDarkMode = !isDarkMode }
                )
            }
        }
    }
}

@Composable
fun CalculatorApp(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val viewModel: CalculatorViewModel = CalculatorViewModel()
    CalculatorScreen(viewModel1 = viewModel, isDarkMode = isDarkMode, onToggleTheme = onToggleTheme)
}
