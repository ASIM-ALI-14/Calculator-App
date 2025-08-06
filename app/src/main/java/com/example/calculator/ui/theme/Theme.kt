package com.example.calculator.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    background = Color(0xAE3B3121),
//    surface = Color(0xFFFFC107),
    onBackground = Color(0xFFFFC107),
    onSurface = Color(0xFF544B3C),
    onSecondaryContainer = Color(0xF7282828),
    secondary = Color(0xFF9B9A9A),
    outline = Color(0xFF48345D),
    outlineVariant = Color(0xFF412D60),
    onError = Color(0xFF987AB9),
    secondaryContainer = Color(0xF71A1A1A),
    surfaceDim = Color(0xFF3A3939),
    surfaceBright = Color(0xFFA965E1),
    inverseSurface = Color(0xBE575757),
    inversePrimary = Color(0xFC6F6F70),
    surfaceContainerLow = Color(0xFFDEDBDB),
    surfaceContainerHigh = Color(0xFFBABDBD),
            errorContainer = Color(0xFA101010)


)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFFFFF),
    background = Color(0xFFF6EDD0),
    onBackground = Color(0xFFB4935E),
    onSurface = Color(0xFFA99777),
    onSecondaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFF5D7A8A),
    outline = Color(0xFFE1D5E9),
    outlineVariant = Color(0xFF764CC7),
    onError = Color(0xFF682E85),
    secondaryContainer = Color(0xBEFFFFFF),
    surfaceDim = Color(0xFF7C7575),
    surfaceBright = Color(0xFF9228E2),
    inverseSurface = Color(0xBE4D4C4C),
    inversePrimary = Color(0xFC4B4B4B),
    surfaceContainerLow = Color(0xFF1C1C1C),
    surfaceContainerHigh = Color(0xFF5E6B70),
    errorContainer = Color(0xBE858484)



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}