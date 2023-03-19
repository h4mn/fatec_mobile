package com.example.fatecplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fatecplayground.ui.CalculadoraScreen
import com.example.fatecplayground.ui.HomeScreen
import com.example.fatecplayground.ui.ProjetosScreen

@Composable
fun PlaygroundNavHost (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home.rota,
        modifier = modifier
   ) {
        composable(route = Home.rota) {
            //Home.tela()
            HomeScreen(navController = navController)
        }
        composable(route = Projetos.rota) {
            Projetos.tela()
        }
        composable(route = Calculadora.rota) {
            Calculadora.tela()
        }
    }
}