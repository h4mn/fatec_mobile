package com.example.fatecplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fatecplayground.ui.CalculadoraScreen
import com.example.fatecplayground.ui.HomeScreen
import com.example.fatecplayground.ui.ProjetosScreen
import com.example.fatecplayground.ui.AboutScreen


@Composable
fun PlaygroundNavHost(
  navController: NavHostController,
  modifier: Modifier = Modifier
) {
  NavHost(
    navController = navController,
    startDestination = Home.rota,
    modifier = modifier
  ) {
    composable(route = Home.rota) {
      HomeScreen(navController = navController)
    }
    composable(route = Projetos.rota) {
      ProjetosScreen(navController = navController)
    }
    composable(route = Calculadora.rota) {
      CalculadoraScreen(navController = navController)
    }
    composable(route = About.rota) {
      AboutScreen(navController = navController)
    }
  }
}