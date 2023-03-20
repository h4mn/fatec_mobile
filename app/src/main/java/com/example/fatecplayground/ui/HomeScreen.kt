package com.example.fatecplayground.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fatecplayground.R
import com.example.fatecplayground.ui.components.Section
import androidx.navigation.compose.rememberNavController

@Composable
@Preview
fun HomeScreen(
  navController: NavController = rememberNavController()
) {
  Column {
    Image(
      /* https://br.freepik.com/vetores-gratis/ilustracao-de-desenvolvimento-de-aplicativo_10354235.htm#query=aplicativo&position=23&from_view=keyword&track=sph */
      /* https://www.canva.com/design/ */
      //bitmap = ImageBitmap.imageResource(id = R.drawable.playground),
      painter = painterResource(id = R.drawable.playground),
      contentDescription = "Logotipo Fatec",
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .fillMaxWidth()
        .size(150.dp)
        .padding(horizontal = 16.dp)
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
      image = ImageBitmap.imageResource(id = R.mipmap.ic_projetos_foreground),
      /* Criado com a ajuda do ChatGPT da OpenAI. */
      description = "Confira todos os projetos desenvolvidos durante o curso de Análise e Desenvolvimento de Sistemas na Fatec, com atividades e exercícios que vão desde programação básica até recursos avançados de desenvolvimento de software.",
      onClick = {
        navController.navigate("Projetos")
      }
    )
  }
}