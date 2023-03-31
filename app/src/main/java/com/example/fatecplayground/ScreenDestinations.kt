package com.example.fatecplayground

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fatecplayground.ui.*

interface ScreenDestination {
    val icone: ImageVector
    val rota: String
    val tela: @Composable () -> Unit
}

object Home : ScreenDestination {
    override val icone = Icons.Filled.Home
    override val rota = "Home"
    override val tela: @Composable () -> Unit = {
        HomeScreen()
    }
}

object Projetos: ScreenDestination {
    override val icone = Icons.Filled.Menu
    override val rota = "Projetos"
    override val tela: @Composable () -> Unit = {
        ProjetosScreen()
    }
}

object Calculadora: ScreenDestination {
    override val icone = Icons.Filled.Edit
    override val rota = "Calculadora"
    override val tela: @Composable () -> Unit = {
        CalculadoraScreen()
    }
}

object About: ScreenDestination {
    override val icone = Icons.Filled.Lock
    override val rota = "About"
    override val tela: @Composable () -> Unit = {
        AboutScreen()
    }
}

object Splash: ScreenDestination {
    override val icone = Icons.Filled.Refresh
    override val rota = "Splash"
    override val tela: @Composable () -> Unit = {
        SplashScreen()
    }
}

val menuScreens = listOf<ScreenDestination>(
    Home,
    Projetos,
    Calculadora
)
