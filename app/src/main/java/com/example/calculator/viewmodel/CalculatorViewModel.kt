package com.example.calculator.viewmodel
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function


import androidx.lifecycle.ViewModel
import com.example.calculator.model.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CalculatorViewModel : ViewModel() {

    private val _state = MutableStateFlow(CalculatorState())
    val state = _state.asStateFlow()

    private var justEvaluated = false

    fun onAction(input: String) {
        val currentState = _state.value

        when (input) {
            "C" -> {
                _state.value = CalculatorState()
                justEvaluated = false
            }

            "=" -> {
                evaluate()
                justEvaluated = true
            }

            "⌫" -> {
                val current = currentState.expression
                if (current.isNotEmpty()) {
                    val newExpr = current.dropLast(1)
                    _state.value = currentState.copy(expression = newExpr)
                    tryAutoEvaluate(newExpr)
                }
            }

            "√" -> {
                val newExpr = currentState.expression + "sqrt("
                _state.value = currentState.copy(expression = newExpr)
            }

            "x²" -> {
                val current = currentState.expression
                if (current.isNotEmpty()) {
                    val newExpr = "($current)^2"
                    _state.value = currentState.copy(expression = newExpr)
                    tryAutoEvaluate(newExpr)
                }
            }

            else -> {
                val isOperator = input in listOf("+", "-", "x", "÷", "*", "/")

                val newExpression = when {
                    justEvaluated && input.first().isDigit() -> {
                        justEvaluated = false
                        input
                    }

                    justEvaluated && input == "." -> {
                        justEvaluated = false
                        "0."
                    }

                    justEvaluated && isOperator -> {
                        justEvaluated = false
                        currentState.expression + input
                    }

                    currentState.result.isNotEmpty() && currentState.expression.isEmpty() -> {
                        currentState.result + input
                    }

                    else -> {
                        currentState.expression + input
                    }
                }

                val shouldClearResult = justEvaluated && !isOperator

                _state.value = currentState.copy(
                    expression = newExpression,
                    result = if (shouldClearResult) "" else currentState.result
                )

                tryAutoEvaluate(newExpression)
            }
        }
    }

    private fun evaluate() {
        try {
            val currentState = _state.value
            val rawExpression = currentState.expression
                .replace("x", "*")
                .replace("÷", "/")
                .let { autoCloseParentheses(it) }

            val result = evalExpression(rawExpression)
            val historyItem = "${currentState.expression} = $result"

            _state.value = currentState.copy(
                expression = result,
                result = "",
                history = listOf(historyItem) + currentState.history
            )

            justEvaluated = true

        } catch (e: Exception) {
            _state.value = _state.value.copy(result = "Error")
            justEvaluated = false
        }
    }

    private fun tryAutoEvaluate(expression: String) {
        if (expression.isEmpty()) return

        val lastChar = expression.last()
        val endsCorrectly = lastChar.isDigit() || lastChar == ')'

        val shouldEvaluate =
            endsCorrectly && (
                    expression.contains(Regex("[+\\-*/÷x]")) ||
                            expression.contains("sqrt(")
                    )

        if (shouldEvaluate) {
            try {
                val exp = expression
                    .replace("x", "*")
                    .replace("÷", "/")
                    .let { autoCloseParentheses(it) }

                val result = evalExpression(exp)
                _state.value = _state.value.copy(result = result)
            } catch (_: Exception) {
                _state.value = _state.value.copy(result = "")
            }
        } else {
            _state.value = _state.value.copy(result = "")
        }
    }


    private fun evalExpression(expression: String): String {
        val sqrtFunction = object : Function("sqrt", 1) {
            override fun apply(vararg args: Double): Double {
                return kotlin.math.sqrt(args[0])
            }
        }

        val expr = ExpressionBuilder(expression)
            .function(sqrtFunction)
            .build()

        val result = expr.evaluate()

        // Format to 3 decimal places
        return if (result % 1.0 == 0.0) {
            result.toInt().toString()
        } else {
            String.format("%.3f", result)
        }
    }



    private fun autoCloseParentheses(expr: String): String {
        val openCount = expr.count { it == '(' }
        val closeCount = expr.count { it == ')' }
        return expr + ")".repeat(openCount - closeCount)
    }
}
