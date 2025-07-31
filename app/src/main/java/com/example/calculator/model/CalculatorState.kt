package com.example.calculator.model



data class CalculatorState(
    val expression: String = "",
    val result: String = "",
    val history: List<String> = emptyList()
)
