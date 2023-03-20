package com.example.fatecplayground.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fatecplayground.DataProjetos.listaDeProjetos

@Composable
@Preview
fun ProjetosScreen(
  navController: NavController = rememberNavController()
) {
  val scrollState = rememberScrollState()
  Column(
    modifier = Modifier.scrollable(
      state = scrollState,
      orientation = Orientation.Vertical
    )
  ) {
    listaDeProjetos.forEach { item ->
      CardListItem(
        concluido = item.concluido,
        titulo = item.titulo,
        description = item.description,
        aoClicar = { navController.navigate(item.rota) }
      )
    }
  }
}

@Composable
fun CardListItem(
  concluido: Boolean,
  titulo: String,
  description: String,
  aoClicar: () -> Unit
) {
  // Regra de negócio
  var iconImage: ImageVector = Icons.Outlined.CheckCircle
  var iconDescription = "Pendente"
  var iconTint: Color = Color.Gray
  if (concluido) {
    iconImage = Icons.Filled.CheckCircle
    iconDescription = "Concluído"
    iconTint = Color.Green
  }
  // Layout
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = aoClicar)
  ) {
    Row(
      verticalAlignment = Alignment.Bottom,
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
    ) {
      Icon(
        imageVector = iconImage,
        contentDescription = iconDescription,
        tint = iconTint,
        modifier = Modifier
          .width(64.dp)
          .size(32.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      CardItemTitulo(titulo)
    }
    Text(
      text = description,
      style = MaterialTheme.typography.body1,
      maxLines = Int.MAX_VALUE,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
  }
}

@Composable
fun CardItemTitulo(texto: String = "Fatec Playground") {
  Row(
    verticalAlignment = CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .height(32.dp)
  ) {
    Text(
      text = texto,
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.subtitle1,
    )
  }
}