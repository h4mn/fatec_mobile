package com.example.fatecplayground.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
@Preview
fun AboutScreen(
  navController: NavController = rememberNavController()
) {
  Column(
    modifier = Modifier
      .fillMaxHeight()
  ) {
    Linha(title = "Fatec",
      description = "Faculdade de Tecnologia de Americana")
    //Spacer(modifier = Modifier.height(8.dp))
    Linha(title = "Autor",
      description = "Hadston Nunes")
    //Spacer(modifier = Modifier.height(8.dp))
    Linha(title = "Fatec Playground",
      description = "https://github.com/h4mn/fatec_mobile")
  }

}

@Composable
fun Linha(
  title: String,
  description: String
) {
  Card(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxWidth(),
    elevation = 8.dp
  ) {
    Column(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
      Text(text = title, fontWeight = FontWeight.Bold)
      Spacer(modifier = Modifier.height(8.dp))
      Row(verticalAlignment = CenterVertically) {
        Text(text = description, style = MaterialTheme.typography.body1)
      }
    }
  }
}

@Composable
fun Greeting(text: String) {
  Text(text = "Hello $text!")
}