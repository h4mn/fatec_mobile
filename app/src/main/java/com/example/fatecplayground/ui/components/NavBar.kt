package com.example.fatecplayground.ui.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fatecplayground.*
import com.example.fatecplayground.ui.components.ui.theme.FatecPlaygroundTheme
import java.util.Locale

@Composable
fun NavBar(
    telas: List<ScreenDestination>,
    aoSelecionar: (ScreenDestination) -> Unit,
    atual: ScreenDestination
) {
    Surface (
        Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ) {
        Row(Modifier.selectableGroup()) {
            telas.forEach {tela ->
                NavBarItem(
                    texto = tela.rota,
                    icone = tela.icone,
                    aoSelecionarItem = {
                        Log.d("NavBar", "tela: ${atual.rota}")
                        aoSelecionar(tela)
                    },
                    selecionado = (tela == atual),
                )
            }
        }
    }
}

@Composable
fun NavBarItem(
    texto: String,
    icone: ImageVector,
    aoSelecionarItem: () -> Unit,
    selecionado: Boolean,
) {
    val cor = MaterialTheme.colors.onSurface
    val tempoAnim = if
        (selecionado) TabFadeInAnimationDuration
        else TabFadeOutAnimationDuration
    val tipoAnim = remember {
        tween<Color>(
            durationMillis = tempoAnim,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val corSelecionado by animateColorAsState(
        targetValue = if (selecionado) cor
            else cor.copy(alpha = InactiveTabOacity),
        animationSpec = tipoAnim
    )
    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selecionado,
                onClick = aoSelecionarItem,
                role = Role.Tab,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = texto }
    ) {
        Icon(
            imageVector = icone,
            contentDescription = texto,
            tint = corSelecionado
        )
        if (selecionado) {
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = texto.uppercase(Locale.getDefault()),
                color = cor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    var currentScreen: ScreenDestination by remember {
        mutableStateOf(Home)
    }
    val navController: NavHostController = rememberNavController()
    FatecPlaygroundTheme {
        NavBar(
            telas = menuScreens,
            aoSelecionar = { novaTela ->
                navController.navigate(novaTela.rota)
            },
            atual = currentScreen
        )
    }
}

private val TabHeight = 56.dp

private const val InactiveTabOacity = 0.60f
private const val TabFadeInAnimationDelay = 100
private const val TabFadeInAnimationDuration = 150
private const val TabFadeOutAnimationDuration = 100
