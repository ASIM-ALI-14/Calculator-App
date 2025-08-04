package com.example.calculator.view


import SingleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculator.viewmodel.CalculatorViewModel


@Composable
fun ButtonPad(viewModel: CalculatorViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val labels = listOf("C", "(", ")", "÷", "√", "%", "x²", "x")
        val labels2 = listOf("7", "8", "9", "4", "5", "6", "1", "2", "3", ".", "0", "⌫")
        val labels3 = listOf("+", "-", "=")// 8 buttons



        Column {
            for (rowIndex in 0 until 2) {
                Row(
                    modifier = Modifier.padding(horizontal = 1.dp, vertical = 1.dp)
                ) {
                    for (colIndex in 0 until 4) {
                        val itemIndex = rowIndex * 4 + colIndex
                        if (itemIndex < labels.size) {
                            val C = labels[itemIndex] == "C"
                            val Skin = labels[itemIndex] in listOf("(", ")", "√", "%", "x²")
                            val LightBlue = labels[itemIndex] in listOf("÷", "x")
                            SingleButton(
                                text = labels[itemIndex],
                                onClick = { viewModel.onAction(labels[itemIndex]) },
//                              BACKGROUNG-COLOR
                                background = if (C) Color(0xFFFFC347)
                                else if (Skin) MaterialTheme.colorScheme.background
                                else if (LightBlue) MaterialTheme.colorScheme.outline
                                else Color.White,
                                shadowColor = if (C) Color(0xFFECA101)
                                else if (Skin) MaterialTheme.colorScheme.onSurface
                                else if (LightBlue) MaterialTheme.colorScheme.outlineVariant
                                else Color.Black,
                                TextColor = if (C) Color.White
                                else if (Skin) MaterialTheme.colorScheme.onBackground
                                else if (LightBlue) MaterialTheme.colorScheme.onError
                                else Color.Black,
                                width = 84.dp,
                                height = 74.dp,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
        Row(modifier = Modifier, verticalAlignment = Alignment.Top) {
            Column {
                for (rowIndex in 0 until 4) {
                    Row(
                        modifier = Modifier.padding(horizontal = 1.dp, vertical = 1.dp)
                    ) {
                        for (colIndex in 0 until 3) {
                            val itemIndex = rowIndex * 3 + colIndex
                            if (itemIndex < labels2.size) {
                                val White = labels2[itemIndex] in listOf(
                                    "7",
                                    "8",
                                    "9",
                                    "4",
                                    "5",
                                    "6",
                                    "1",
                                    "2",
                                    "3",
                                    ".",
                                    "0"
                                )
                                val isEquals2 = labels2[itemIndex] == "⌫"
                                SingleButton(
                                    text = labels2[itemIndex],
                                    onClick = { viewModel.onAction(labels2[itemIndex]) },
                                    width = 84.dp,
                                    background = if (White) MaterialTheme.colorScheme.onSecondaryContainer
                                    else if (isEquals2) MaterialTheme.colorScheme.secondaryContainer
                                    else Color.White,
                                    TextColor = if (White) MaterialTheme.colorScheme.secondary
                                    else if (isEquals2) MaterialTheme.colorScheme.surfaceDim
                                    else Color.Black,
                                    shadowColor = if (White) Color(0x4F939292)
                                    else if (isEquals2) Color(0xFF6C6C6C)
                                    else Color.White,
                                    height = 74.dp,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
            }
            Column {
                for (rowIndex in 0 until 4) {
                    Row(
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        for (colIndex in 0 until 1) {
                            val itemIndex = rowIndex * 1 + colIndex
                            if (itemIndex < labels3.size) {
                                val isEquals = labels3[itemIndex] == "="

                                val Blue = labels3[itemIndex] in listOf("+", "-")

                                SingleButton(
                                    text = labels3[itemIndex],
                                    onClick = { viewModel.onAction(labels3[itemIndex]) },


                                    modifier = Modifier,
                                    background = if (Blue) MaterialTheme.colorScheme.outline
                                    else if (isEquals) MaterialTheme.colorScheme.surfaceBright
                                    else Color.White,
                                    TextColor = if (Blue) MaterialTheme.colorScheme.onError
                                    else if (isEquals) Color.White
                                    else Color.Black,
                                    shadowColor = if (Blue) MaterialTheme.colorScheme.outlineVariant
                                    else if (isEquals) Color(0xFF7861AD)
                                    else Color.White,
                                    height = if (isEquals) 162.dp else 74.dp,
                                    width = 78.dp,


                                    )
                            }

                        }
                    }
                }
            }

        }
    }
}