package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.debugInspectorInfo
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

/**
    * A classe MainActivity é a classe principal do aplicativo.
    * Ela é responsável por criar a interface do usuário.
    * Neste exemplo, a interface do usuário é criada usando o Jetpack Compose.
    * https://developer.android.com/jetpack/compose/tutorial
    * https://developer.android.com/jetpack/compose/tooling?hl=pt-br
    *
    * @property savedInstanceState
    * @event onCreate Cria a interface do usuário.
    * @method setContent Define o conteúdo da interface do usuário.
    * @return Unit
    * @see https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity
    * @author Hads 2023
    */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Surface {
                    MessageCard(
                        Mensagem("Android", "Jetpack Compose")
                    )
                }
            }
        }
    }
}

/**
    * A função Modifier.myDebug() é uma função de extensão.
    * Ela é responsável por criar um Modificador personalizado.
    * Neste exemplo, o Modificador personalizado é responsável por criar um fundo azul e preencher a largura máxima.
    *
    * @property Modifier.background Color.Cyan
    * @property Modifier.fillMaxWidth()
    * @return Modifier
    * @see https://developer.android.com/reference/kotlin/androidx/compose/ui/package-summary#(androidx.compose.ui.Modifier).composed(kotlin.Function1,kotlin.Function1)
    * @author Hads 2023
    */
@Composable
fun Modifier.myDebug() = composed(
    inspectorInfo = debugInspectorInfo {
        name = "Meu Debugger"
    }
) {
    Modifier
        .background(Color.Cyan)
        .fillMaxWidth()
}

/**
    * A função MessageCard() é uma função Composable.
    * Ela é responsável por criar um Card com uma imagem e um texto.
    * Neste exemplo, o Card contém um layout de linha com uma imagem e um layout de coluna com um texto.
    * https://developer.android.com/jetpack/compose/tutorial
    *
    * @property m
    * @property Row
    * @property Image
    * @property Column
    * @property Text
    * @property Modifier.padding
    * @see https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary#Column(androidx.compose.ui.Modifier,androidx.compose.foundation.layout.Arrangement.Vertical,androidx.compose.ui.Alignment.Horizontal,kotlin.Function1)(androidx.compose.ui.Modifier,androidx.compose.foundation.layout.Arrangement.Vertical,androidx.compose.ui.Alignment.Horizontal,kotlin.Function1)
    * @author Hads 2023
    */
@Composable
fun MessageCard(
    m: Mensagem
) {
    Row(
        modifier = Modifier.padding(all = 32.dp),
    ) {
        Image(
            painter = painterResource(id = R.mipmap.cat_1),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp,
                    MaterialTheme.colors.secondary,
                    CircleShape
                )
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary
            else MaterialTheme.colors.surface
        )

        Column (
            modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }
        ) {
            Text(
                text = m.autor,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = m.corpo,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }

    }
}

/**
    * A classe Mensagem é uma classe de dados.
    * Ela é responsável por armazenar as informações de uma mensagem.
    * Neste exemplo, a classe Mensagem armazena o autor e o corpo da mensagem.
    * https://medium.com/@jeffersontpadua/kotlin-data-classes-para-desenvolvedores-android-3928926aaa5e
    *
    * @property autor
    * @property corpo
    * @see https://kotlinlang.org/docs/data-classes.html
    * @author Hads 2023
    */
 data class Mensagem(val autor: String, val corpo: String)

/**
 * A função Conversation() é uma função Composable.
 * Ela é responsável por criar uma lista de Cards com uma imagem e um texto.
 * Neste exemplo, a lista de Cards contém um layout de coluna com uma imagem e um layout de coluna com um texto.
 *
 * @author Hads 2023
 */
@Composable
fun Conversa(l: List<Mensagem>) {
        LazyColumn {
            items(l) {
                    l_msg -> MessageCard(m = l_msg)
            }
        }
}

/**
    * A função HelloContent() é uma função Composable.
    * Ela é responsável por criar um Card com um campo de texto.
    * Neste exemplo, o Card contém um layout de coluna com um texto e um campo de texto.
    * https://developer.android.com/jetpack/compose/tutorial
    *
    * @property Column
    * @property Text
    * @property OutlinedTextField
    * @property Modifier.padding
    * @see https://developer.android.com/reference/kotlin/androidx/compose/ui/package-summary#(androidx.compose.ui.Modifier).composed(kotlin.Function1,kotlin.Function1)
    * @author Hads 2023
    */
@Composable
fun HelloContent() {
    Column(
        modifier = Modifier
        .padding(16.dp)
    ) {
        Text(
            text = "Hello",
            modifier = Modifier
                .padding(bottom = 8.dp)
            ,
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Name")},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
    * A função PreviewMainActivity() é uma função Composable.
    * Ela é responsável por criar uma pré-visualização da interface do usuário.
    * Neste exemplo, a pré-visualização serve para testar a interface do usuário.
    * https://developer.android.com/jetpack/compose/tutorial
    *
    * @property Column
    * @method HelloContent()
    * @method MessageCard()
    * @see https://developer.android.com/jetpack/compose/preview?hl=pt-br#:~:text=Para%20criar%20uma%20visualiza%C3%A7%C3%A3o%20de,no%20painel%20Visualiza%C3%A7%C3%A3o%20do%20Studio.&text=Text%20(text%20%3D%20%22Hello%20%24name!%22)
    * @author Hads 2023
    */
//@Preview(name = "Light Mode")
///*
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMainActivity() {
    ComposeTutorialTheme {
        Surface {
            Column {
                HelloContent()
                Spacer(modifier = Modifier.height(16.dp))
                MessageCard(
                    m = Mensagem(
                        "Hads",
                        "Treinando uma coisa Legal"
                    )
                )
                Conversa(l = Amostra.amostraDeConversa)
            }
        }
    }
}
//*/
//Falta exemplo de como utilizar Box

