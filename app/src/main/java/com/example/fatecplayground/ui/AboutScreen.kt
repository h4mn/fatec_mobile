package com.example.fatecplayground.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
@Preview
fun AboutScreen(
  navController: NavController = rememberNavController()
) {
  Greeting(name = "404")
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}