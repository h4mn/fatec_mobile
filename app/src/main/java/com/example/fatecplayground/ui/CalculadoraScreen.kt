@file:OptIn(ExperimentalTextApi::class)

package com.example.fatecplayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fatecplayground.R
import kotlin.random.Random

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
  providerAuthority = "com.google.android.gms.fonts",
  providerPackage = "com.google.android.gms",
  certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Orbitron")

@Suppress("DEPRECATION")
val fontFamily = FontFamily(
  Font(googleFont = fontName, fontProvider = provider)
)

@Composable
@Preview
fun CalculadoraScreen(
  navController: NavController = rememberNavController()
) {
  // Lógica
  var valor_1 = remember { mutableStateOf(0) }
  var valor_2 = remember { mutableStateOf(0) }
  var valor_resultado = remember { mutableStateOf(0) }
  val aoMudar_valor_1: (Int) -> Unit = { it -> valor_1.value = it }
  val aoMudar_valor_2: (Int) -> Unit = { it -> valor_2.value = it }
  val aoClicar_Somar: () -> Unit = {
    val resultado = valor_1.value + valor_2.value
    valor_resultado.value = resultado
  }
  val aoClicar_Limpar: () -> Unit = {
    valor_1.value = 0
    valor_2.value = 0
    valor_resultado.value = 0
  }
  val aoClicar_Random: () -> Unit = {
    val random = Random
    if (valor_1.value > 0) {
      "${valor_1.value}${random.nextInt(10)}"
        .also { valor_1.value = it.toInt() }
    } else {
      valor_1.value = random.nextInt(10)
    }
    // Pesquisar como se pega o foco de um TextField
    // para colocar o numero aleatorio no TextField com foco
  }
  // Design
  Column(
    modifier = Modifier
      .padding(16.dp)
      //.myDebug()
  ) {
    CalcVisor("Resultado", valor_resultado.value.toString())
    Spacer(modifier = Modifier.height(8.dp))
    CardEdit(
      rotulo = "Valor 1",
      valor = valor_1.value.toString(),
      aoMudar = { aoMudar_valor_1(it) },
    )
    Spacer(modifier = Modifier.height(8.dp))
    CardEdit(
      rotulo = "Valor 2",
      valor = valor_2.value.toString(),
      aoMudar = { aoMudar_valor_2(it) },
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row {
      CalcButton("+", { aoClicar_Somar() })
      Spacer(modifier = Modifier.width(10.dp))
      CalcButton("CE", { aoClicar_Limpar() })
      Spacer(modifier = Modifier.width(10.dp))
      CalcButton("?", { aoClicar_Random() })
    }
  }
}

@Composable
fun CardEdit(
  rotulo: String,
  valor: String,
  aoMudar: (Int) -> Unit,
) {
  OutlinedTextField(
    value = valor,
    onValueChange = { aoMudar(it.toInt()) },
    label = { Text(text = rotulo) },
    keyboardOptions = KeyboardOptions.Default.copy(
      keyboardType = KeyboardType.Number
    ),
    textStyle = TextStyle(
      fontSize = 30.sp,
      fontFamily = FontFamily.Monospace,
    ),
    modifier = Modifier.fillMaxWidth()
  )
}

@Composable
fun CalcVisor(
  rotulo: String,
  valor: String
) {
  OutlinedTextField(
    value = valor,
    onValueChange = {},
    label = { Text(text = rotulo) },
    readOnly = true,
    enabled = false,
    textStyle = TextStyle(
      fontSize = 50.sp,
      fontFamily = fontFamily,
      textAlign = TextAlign.Right,
    ),
    modifier = Modifier
      .fillMaxWidth()
      .background(Color(0xFF80A17C))
      
  )
}

@Composable
fun CalcButton(
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