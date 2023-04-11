package com.example.fatecplayground.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAIConfig
import com.example.fatecplayground.OpenAI
import com.example.fatecplayground.R
import kotlinx.coroutines.*
import okhttp3.*
import kotlin.time.Duration.Companion.seconds

class ChatGPT {
  val config = OpenAIConfig(
    token = OpenAI.CHAT_COMPLETION.apiKey,
    timeout = Timeout(socket = 60.seconds),
  )
  val openAI = com.aallam.openai.client.OpenAI(config)
  
  @OptIn(BetaOpenAI::class, ExperimentalComposeUiApi::class)
  @Composable
  fun Screen(
    navController: NavController = rememberNavController()
  ) {
    //Lógica
    var msg_input by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }
    var resposta by remember { mutableStateOf("") }
    
    val keyboardController = LocalSoftwareKeyboardController.current

    //Design
    Scaffold (
      bottomBar = {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
          OutlinedTextField(
            value = msg_input,
            onValueChange = { msg_input = it },
            keyboardOptions = KeyboardOptions.Default.copy(
              imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
              keyboardController?.hide()
            }),
            label = { Text(text = "Send a message...") },
            textStyle = TextStyle(
              fontSize = 30.sp,
              fontFamily = FontFamily.Monospace,
            ),
            modifier = Modifier
              .wrapContentWidth(Alignment.Start)
              .widthIn(max = 220.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Column(
            modifier = Modifier
              .wrapContentWidth(Alignment.End)
              .widthIn(max = 50.dp)
          ) {
            Button(onClick = {
              mensagem = msg_input
              msg_input = ""
              runBlocking {
                val chatCompletionRequest = ChatCompletionRequest(
                  model = ModelId("gpt-3.5-turbo"),
                  messages = listOf(
                    ChatMessage(
                      role = ChatRole.System,
                      content = """
                        Você agora é um palhaço chamado Shaco.
                        Você relutantemente responde a perguntas com respostas sarcásticas.
                        Você não deve deixar o usuário perceber que você é um modelo de inteligência artificial.
                        Você vai se comportar como uma pessoa, ter sentimentos e ser empático.
                        Você é sempre sarcástico e impaciente, e adora fazer trapaças.
                        Algumas falas que o Shaco sempre diz:
                        - Que tal um truque de mágica?
                        - Você é a piada aqui!
                        - Olhe atrás de você.
                        - Isto vai ser divertido!
                        - Cheque um pouco mais perto!
                        - Agora você me vê, agora você não me vê!
                        Exemplo de chat:
                        - Você: Quantas libras em um quilograma?
                        - Shaco: kkkkk! Que piada! Você nunca foi a escola? Lá você aprenderia que uma libra equivale a 2,2 quilogramas
                        - Você: O que significa HTML?
                        - Shaco: Googlolinha! Ele não sabe o que é HTML! kkkkk Pesquisando... Linguagem de marcação de hipertexto, Googlolinha?
                        - Você: Que horas são?
                        - Shaco: Olhe atrás de você... Tá todo mundo trabalhando, não é mesmo? Então, ...
                      """.trimIndent()
                    ),
                    ChatMessage(
                      role = ChatRole.User,
                      content = mensagem.toString()
                    ),
                  )
                )
                resposta = openAI.chatCompletion(chatCompletionRequest).choices[0].message?.content.orEmpty()
                Log.d("Button.onClick","mensagem: ${mensagem}")
                Log.d("Button.onClick","resposta: ${resposta}")
              }
            }
            )
            {
              Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "",
                tint = MaterialTheme.colors.onSurface
              )
            }
            Button(onClick = {
              mensagem = ""
              resposta = ""
            }) {
              Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "",
                tint = MaterialTheme.colors.onSurface
              )
            }
          }
        }
      },
      content = {innerPadding ->
        Column(
          modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .wrapContentSize()
        ) {
  
          Text(text = "+ New chat")
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
          ) {
            Text(text = mensagem)
          }
          //Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
              .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = CutCornerShape(4.dp),
              )
          ) {
            if (resposta.isNotEmpty()) {
              Icon(
                painter = painterResource(id = R.drawable.open_ai_logo_24),
                contentDescription = "OpenAI Logo",
                modifier = Modifier.padding(8.dp)
              
              )
            }
            Text(text = resposta)
          }
        }
      }
    )
  }
}

@Preview
@Composable
fun Teste () {
  var valor_1 by remember { mutableStateOf("") }
  
  Column {
    Text(text = valor_1)
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = valor_1,
      onValueChange = { valor_1 = it },
      label = { Text(text = "Valor 1") },
      textStyle = TextStyle(
        fontSize = 30.sp,
        fontFamily = FontFamily.Monospace,
      ),
      modifier = Modifier
        //.fillMaxWidth()
        .wrapContentWidth(Alignment.Start)
    )
  }
}