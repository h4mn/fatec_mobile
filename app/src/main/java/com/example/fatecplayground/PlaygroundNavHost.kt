package com.example.fatecplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fatecplayground.ui.*


@Composable
fun PlaygroundNavHost(
  navController: NavHostController,
  modifier: Modifier = Modifier
) {
  NavHost(
    navController = navController,
    startDestination = "",
    modifier = modifier
  ) {
    composable(route = Main.rota) {
      MainScreen(navController = navController)
    }
    
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