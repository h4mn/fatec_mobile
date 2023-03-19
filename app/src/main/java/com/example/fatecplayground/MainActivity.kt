package com.example.fatecplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                topBar = { myTopBar("Fatec Playground") },
                bottomBar = {
                    NavBar(
                        telas = menuScreens,
                        aoSelecionar = { newScreen ->
                            navController.navigate(newScreen.rota)
                        },
                        atual = currentScreen
                    )
                    //myBottomBar(lista = Atividades.listaDeAtividades)
                }
            ) { contentPadding ->
                PlaygroundNavHost(
                    navController = navController,
                    modifier = Modifier.padding(contentPadding)
                )
                /*
                NavHost(
                    navController = navController,
                    startDestination = Home.rota,
                    modifier = Modifier.padding(contentPadding)
                ) {
                    composable(route = Home.rota) {
                        Home.tela()
                    }
                    composable(route = Projetos.rota) {
                        Projetos.tela()
                    }
                    composable(route = Calculadora.rota) {
                        Calculadora.tela()
                    }
                }
                */
                //MainFragment(scaffoldPadding = contentPadding)
            }
        }
    }
}

@Composable
fun HomeFragment(scaffoldPadding: PaddingValues) {
    Box(modifier = Modifier.padding(scaffoldPadding)) {

    }
}

@Composable
fun CalculatorFragment(scaffoldPadding: PaddingValues) {
    Box(modifier = Modifier.padding(scaffoldPadding)) {
        Column {
            Text(text = "Activity: " + currentActivity)
            Greeting("CalculatorFragment")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun myTopBar (titulo: String) {
    TopAppBar {
        Icon(painter = painterResource(
            id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = titulo,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun myBottomBar (lista: List<Atividade>) {
    BottomAppBar {
        LazyRow {
            items(lista) { listaAtividades ->
                myMenu(a = listaAtividades)
            }
        }
    }
}

@Composable
fun myMenu (a: Atividade) {
    var currentAtividade by remember { mutableStateOf("") }
    Row (
        modifier = Modifier.clickable {
            currentAtividade = a.Nome
            currentActivity = currentAtividade
        }
    ) {
        Text(text = a.Nome)
        Spacer(modifier = Modifier.width(10.dp))
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