package com.example.fatecplayground.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fatecplayground.ui.ui.theme.FatecPlaygroundTheme
import androidx.navigation.compose.rememberNavController
import com.example.fatecplayground.MainScreen
import com.example.fatecplayground.Splash

/*
* https://acervolima.com/tela-inicial-animada-no-android-usando-o-jetpack-compose/
* */
@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    
    super.onCreate(savedInstanceState)
    setContent {
  
      val navController = rememberNavController()
      
      NavHost(
        navController = navController,
        startDestination = Splash.rota
      ) {
        composable(Splash.rota) {
          SplashScreen(navController)
        }
        composable("MainScreen") {
          MainScreen()
        }
      }
      
    }
  }
}

@Composable
fun SplashScreen(
  navController: NavController = rememberNavController()
) {
  fun invoke() {
    Handler(Looper.getMainLooper()).postDelayed({
      navController.navigate("MainScreen")
    }, 3000)
  }
  
  FatecPlaygroundTheme {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
      CalcVisor("Android Jetpack Compose", "Hello!")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  FatecPlaygroundTheme {
    CalcVisor("Android", "Hello!")
  }
}