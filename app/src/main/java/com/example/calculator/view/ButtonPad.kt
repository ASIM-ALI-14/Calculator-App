package com.example.calculator.view


import SingleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculator.viewmodel.CalculatorViewModel


@Composable
fun ButtonPad(viewModel: CalculatorViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(bottom = 12.dp),
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
                                else if (Skin) Color(0xFFF5F0E4)
                                else if (LightBlue) Color(0xFFE1D5E9)
                                else Color.White,
                                shadowColor = if (C) Color(0xFFECA101)
                                else if (Skin) Color(0xFFA99777)
                                else if (LightBlue) Color(0xFF764CC7)
                                else Color.Black,
                                TextColor = if (C) Color.White
                                else if (Skin) Color(0xFFB4935E)
                                else if (LightBlue) Color(0xFF682E85)
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
                                    background = if (White) Color(0xFFFFFFFF)
                                    else if (isEquals2) Color(0xBEFFFFFF)
                                    else Color.White,
                                    TextColor = if (White) Color(0xFF5D7A8A)
                                    else if (isEquals2) Color(0xFF7C7575)
                                    else Color.Black,
                                    shadowColor = if (White) Color(0xF7B0B0B0)
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
                                    background = if (Blue) Color(0xFFE1D5E9)
                                    else if (isEquals) Color(0xFF9228E2)
                                    else Color.White,
                                    TextColor = if (Blue) Color(0xFF682E85)
                                    else if (isEquals) Color.White
                                    else Color.Black,
                                    shadowColor = if (Blue) Color(0xFF7058A8)
                                    else if (isEquals) Color(0xFF7058A8)
                                    else Color.White,
                                    height = if (isEquals) 162.dp else 74.dp,
                                    width = 84.dp,


                                    )
                            }

                        }
                    }
                }
            }

        }
    }
}