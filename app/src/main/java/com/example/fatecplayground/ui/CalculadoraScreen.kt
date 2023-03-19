package com.example.fatecplayground.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fatecplayground.MainScreen

@Composable
fun CalculadoraScreen(
    onClickSeeAllAccounts: () -> Unit = {},
    onClickSeeAllBills: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
) {
    var valor_1 = remember { mutableStateOf(0) }
    var valor_2 = remember { mutableStateOf(0) }
    var valor_resultado = remember { mutableStateOf(0) }

    val aoMudar_valor_1: (Int) -> Unit = {it -> valor_1.value = it }
    val aoMudar_valor_2: (Int) -> Unit = {it -> valor_2.value = it }
    val aoClicar_Somar: () -> Unit = {
        val resultado = valor_1.value + valor_2.value
        valor_resultado.value = resultado
    }
    val aoClicar_Limpar: () -> Unit = {
        valor_1.value = 0
        valor_2.value = 0
        valor_resultado.value = 0
    }

    Column (
        //modifier = Modifier.myDebug()
    ) {
        Text(text = "Valor 1")
        TextField(
            value = valor_1.value.toString(),
            onValueChange = { aoMudar_valor_1(it.toInt()) },
            label = { Text(text = "Insira o valor 1") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Valor 2")
        TextField(
            value = valor_2.value.toString(),
            onValueChange = { aoMudar_valor_2(it.toInt()) },
            label = { Text(text = "Insira o valor 2") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        CalcVisor("Resultado", valor_resultado.value.toString() )
        Row {
            CalcButton("+", { aoClicar_Somar() } )
            Spacer(modifier = Modifier.width(10.dp))
            CalcButton("CE", { aoClicar_Limpar() } )
        }
    }
}

@Composable
fun CalcVisor (label: String, text: String) {
    Card(
        shape = RoundedCornerShape(10),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF80A17C))
            .border(BorderStroke(2.dp, Color.DarkGray))
    ) {
        Text(text = label)
        Text(
            text = text,
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Right,
            ),
            //color = Color.White.copy(alpha = 0.9f),
            //color = Color.Transparent,
            //modifier = Modifier.alpha(alpha = 0.9f)
        )
    }
}

@Composable
fun CalcButton (
    text: String,
    aoClicar: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(10),
        onClick = { aoClicar() }
    ) {
        Text(
            text = text,
            fontSize = 30.sp
        )
    }
}

@Composable
fun Modifier.myDebug() = composed(
    inspectorInfo = debugInspectorInfo {
        name = "Meu Debugger"
    }
) {
    Modifier
        .background(Color.Cyan)
        .fillMaxWidth()
}

@Preview
@Composable
fun CalculadoraPreview () {
    MainScreen()
}