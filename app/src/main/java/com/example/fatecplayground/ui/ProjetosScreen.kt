package com.example.fatecplayground.ui

import androidx.compose.runtime.Composable

@Composable
fun ProjetosScreen() {
    //
}



data class Projeto(
    val id: Int,
    val titulo: String,
    val concluido: Boolean
)

object Projetos {
    val listaDeProjetos = listOf(
        Projeto(1, "Fatec Playground", true),
        Projeto(2, "Calculadora", true),
        Projeto(3, "Sorteio de #0 a #10", false)
    )
}