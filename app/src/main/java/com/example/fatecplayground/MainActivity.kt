package com.example.fatecplayground

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.widget.Toast
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fatecplayground.ui.components.NavBar
import com.example.fatecplayground.ui.theme.FatecPlaygroundTheme


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    setContent {
      FatecPlaygroundTheme {
        var isVisible by remember { mutableStateOf(true) }
        if (isVisible) {
          SetAndroidStatusBar(false, MaterialTheme.colors.primary)
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(MaterialTheme.colors.primary)
          ) {
            val composition by rememberLottieComposition(
              LottieCompositionSpec.RawRes(R.raw.logo)
            )
            val logoAnimationState = animateLottieCompositionAsState(
              composition = composition
            )
            LottieAnimation(
              composition = composition,
              progress = { logoAnimationState.progress }
            )
            if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
              isVisible = false
            }
          }
        } else {
          SetAndroidStatusBar(true,MaterialTheme.colors.primaryVariant)
          if (ContextCompat.checkSelfPermission(
              this, Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED
          ) {
            ActivityCompat.requestPermissions(
              this,
              arrayOf(Manifest.permission.INTERNET),
              1
            )
          }
          MainScreen()
        }
      }
    }
  }
  
  override fun onRequestPermissionsResult(
    requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
      1 -> {
        if ((grantResults.isNotEmpty()
                  && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        ) {
        
        } else {
          val toast = Toast.makeText(
            applicationContext,
            "Não será possível utilizar o ChatGPT!",
            Toast.LENGTH_SHORT
          )
          toast.show()
          Handler(Looper.getMainLooper()).postDelayed(
            {
              toast.cancel()
            }, 3000
          )
        }
      }
    }
    return
  }
}

@Composable
fun SetAndroidStatusBar(visible: Boolean, cor: Color) {
  val window: Window = (LocalContext.current as Activity).window
  //val backgroundColor = MaterialTheme.colors.primary
  WindowCompat.setDecorFitsSystemWindows(window, visible)
  window.statusBarColor = cor.toArgb()
}

@Composable
fun MainScreen() {
  val navHostController = rememberNavController()
  val currentBackStack by navHostController.currentBackStackEntryAsState()
  val currentDestination = currentBackStack?.destination
  val currentScreen = menuScreens.find { it.rota == currentDestination?.route } ?: Home
  Surface(
    //modifier = Modifier.fillMaxSize(),
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