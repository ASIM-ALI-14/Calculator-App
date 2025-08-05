package com.example.calculator

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.view.CalculatorScreen
import com.example.calculator.viewmodel.CalculatorViewModel
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // ✅ This line must come BEFORE super.onCreate
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }

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
    // ✅ Use AndroidX ViewModel scoped to activity
    val viewModel: CalculatorViewModel = viewModel()

    CalculatorScreen(
        viewModel1 = viewModel,
        isDarkMode = isDarkMode,
        onToggleTheme = onToggleTheme
    )
}
