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
            Home.tela()
            /*
            HomeScreen(
                onClickSeeAllAccounts = {
                    navController.navigate(Projetos.rota)
                },
                onClickSeeAllBills = {
                    navController.navigate(Projetos.rota)
                },
                onAccountClick = {variavel ->
                    navController.navigate(variavel)
                }
            )
            */
        }
        composable(route = Projetos.rota) {
            Projetos.tela()
            //ProjetosScreen()
        }
        composable(route = Calculadora.rota) {
            Calculadora.tela()
            //CalculadoraScreen()
        }
    }
}