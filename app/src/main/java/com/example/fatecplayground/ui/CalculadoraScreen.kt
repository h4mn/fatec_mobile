package com.example.fatecplayground.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fatecplayground.Greeting
import com.example.fatecplayground.MainScreen

@Composable
fun CalculadoraScreen(
    onClickSeeAllAccounts: () -> Unit = {},
    onClickSeeAllBills: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
) {
    val valor_1: String by remember { mutableStateOf("1") }
    val valor_2: String by remember { mutableStateOf("2") }
    val valor_resultado: String by remember { mutableStateOf("resultado") }
    Column {
        Text(text = "Valor 1")
        TextField(
            value = valor_1,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Valor 2")
        TextField(
            value = valor_2,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Resultado")
        TextField(
            value = valor_resultado,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Button(

            onClick = { /*TODO*/ }
        ) {

        }
    }
}

@Composable
fun Visor (t: String) {
    Card {
        Text(
            text = t,
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Right
            ),
        )
    }
}

@Preview
@Composable
fun CalculadoraPreview () {
    MainScreen()
}