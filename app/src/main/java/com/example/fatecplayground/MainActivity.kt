package com.example.fatecplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fatecplayground.ui.CalcVisor
import com.example.fatecplayground.ui.components.NavBar
import com.example.fatecplayground.ui.theme.AnimatedSplashScreenTheme

var currentActivity: String = ""

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    setContent {
      val navController = rememberNavController()
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colors.primary)
      ) {
    
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo))
        val logoAnimationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
          composition = composition,
          progress = { logoAnimationState.progress }
        )
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
          navController.navigate(Main.rota)
        }
      }
    }

  }
}


@Composable
fun MainScreen(navController: NavController = rememberNavController()) {
  val navHostController = rememberNavController()
  val currentBackStack by navHostController.currentBackStackEntryAsState()
  val currentDestination = currentBackStack?.destination
  val currentScreen = menuScreens.find { it.rota == currentDestination?.route } ?: Home
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    Scaffold(
      topBar = {
        TopAppBar {
          Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
              .width(64.dp)
              .size(32.dp)
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
            navHostController.navigate(newScreen.rota)
          },
          atual = currentScreen
        )
      }
    ) { contentPadding ->
      PlaygroundNavHost(
        navController = navHostController,
        modifier = Modifier.padding(contentPadding)
      )
    }
  }
}