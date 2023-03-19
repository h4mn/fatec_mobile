package com.example.fatecplayground.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.fatecplayground.MainScreen
import com.example.fatecplayground.R
import com.example.fatecplayground.ui.components.Section
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController()
) {
    Column {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_banner),
            contentDescription = "Logotipo Fatec",
            modifier = Modifier.fillMaxWidth()
        )
        Section(
            title = "Calculadora",
            /* from https://www.flaticon.com/br/icones-gratis/calculadora */
            image = ImageBitmap.imageResource(id = R.mipmap.ic_calculadora),
            /* Criado com a ajuda do ChatGPT da OpenAI. */
            description = "Em destaque, o resultado da atividade da Calculadora Simples seguindo o tutorial de três aulas desenvolvido em Kotlin utilizando o framework Compose.",
            onClick = {
                navController.navigate("Calculadora")
            }
        )
        Section(
            title = "Projetos",
            /* from https://br.freepik.com/psd-premium/icone-de-lista-de-tarefas-bloco-de-notas-com-lista-de-tarefas-concluida-e-renderizacao-em-3d-de-lapis-psd-premium_26142800.htm */
            image = ImageBitmap.imageResource(id = R.mipmap.ic_projetos),
            /* Criado com a ajuda do ChatGPT da OpenAI. */
            description = "Confira todos os projetos desenvolvidos durante o curso de Análise e Desenvolvimento de Sistemas na Fatec, com atividades e exercícios que vão desde programação básica até recursos avançados de desenvolvimento de software.",
            onClick = {
                navController.navigate("Projetos")
            }
        )
    }
}

@Composable
@Preview
fun PreviewHome () {
    MainScreen()
}