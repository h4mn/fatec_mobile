package com.example.fatecplayground.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .align(CenterHorizontally)
    )
    {
      Text(
        text = "Hadston Nunes",
        modifier = Modifier
          .fillMaxHeight()
          .align(CenterVertically)
      )
    }
  }

}

@Composable
fun Greeting(text: String) {
  Text(text = "Hello $text!")
}