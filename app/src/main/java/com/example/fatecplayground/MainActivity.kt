package com.example.fatecplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fatecplayground.ui.components.NavBar
import com.example.fatecplayground.ui.theme.FatecPlaygroundTheme

var currentActivity: String = ""

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    FatecPlaygroundTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = menuScreens.find { it.rota == currentDestination?.route } ?: Home
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar {
                        Icon(painter = painterResource(
                            id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Fatec Playground",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                },
                bottomBar = {
                    NavBar(
                        telas = menuScreens,
                        aoSelecionar = { newScreen ->
                            navController.navigate(newScreen.rota)
                        },
                        atual = currentScreen
                    )
                }
            ) { contentPadding ->
                PlaygroundNavHost(
                    navController = navController,
                    modifier = Modifier.padding(contentPadding)
                )
            }
        }
    }
}

data class Atividade(val Nome: String)

object Atividades {
    val listaDeAtividades = listOf(
        Atividade("Home"),
        Atividade("Projetos"),
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FatecPlaygroundTheme {
        MainScreen()
    }
}