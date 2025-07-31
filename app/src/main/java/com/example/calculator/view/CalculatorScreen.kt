package com.example.calculator.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.viewmodel.CalculatorViewModel


@Composable
fun CalculatorScreen(viewModel1: CalculatorViewModel) {
    val viewModel: CalculatorViewModel = CalculatorViewModel()
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.background(color = Color.White)
            .fillMaxSize()
            .padding(bottom = 45.dp, start = 10.dp, end = 10.dp, top = 16.dp),

    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Text(
                text = state.expression,
                style = TextStyle(fontSize = 32.sp), fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 73.dp, top = 62.dp)
            )
            Text(
                text = "${state.result}",
                color = Color.Gray,

                style = TextStyle(fontSize = 42.sp, fontWeight = FontWeight.SemiBold)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                thickness = 2.dp,
                color = Color(0xBEE7E7E7)
            )



            ButtonPad(viewModel)
        }
    }
}



