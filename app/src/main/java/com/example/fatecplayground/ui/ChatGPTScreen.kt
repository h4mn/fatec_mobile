package com.example.fatecplayground.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import okhttp3.*
import kotlin.time.Duration.Companion.seconds

class ChatGPT {
  val config = OpenAIConfig(
    token = OpenAI.CHAT_COMPLETION.apiKey,
    timeout = Timeout(socket = 60.seconds),
  )
  val openAI = com.aallam.openai.client.OpenAI(config)
  
  @OptIn(BetaOpenAI::class)
  @Composable
  fun Screen(
    navController: NavController = rememberNavController()
  ) {
    //Lógica
    var mensagem by remember { mutableStateOf("") }
    var resposta by remember { mutableStateOf("") }

    //Design
    Scaffold (
      bottomBar = {
        Row(modifier = Modifier.fillMaxWidth()) {
          OutlinedTextField(
            value = "",
            onValueChange = {
              mensagem = mensagem + it
            },
            label = { Text(text = "Send a message...") },
            keyboardOptions = KeyboardOptions.Default.copy(
              keyboardType = KeyboardType.Text
            ),
            textStyle = TextStyle(
              fontSize = 30.sp,
              fontFamily = FontFamily.Monospace,
            ),
            modifier = Modifier
              //.fillMaxWidth()
              .wrapContentWidth(Alignment.Start)
          )
          Button(onClick = {
            runBlocking {
              val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
//                  ChatMessage(
//                    role = ChatRole.System,
//                    content = "Você é um assistente prestativo que alegra e diverte a todos."
//                  ),
                  ChatMessage(
                    role = ChatRole.User,
                    content = mensagem.toString()
                  ),
                )
              )
              resposta = openAI.chatCompletion(chatCompletionRequest).choices[0].message?.content.orEmpty()
              Log.d("Button.onClick","mensagem: ${mensagem}")
              Log.d("Button.onClick","resposta: ${resposta}")

//              openAI.chatCompletions(chatCompletionRequest)
//                .onEach {
//                  //resposta = it.choices.first().delta?.content.orEmpty()
//                  resposta = it.choices[0].delta?.content.orEmpty()
//                }
//                .onCompletion {
//                  Log.d("Button.onClick","mensagem: ${mensagem}")
//                  Log.d("Button.onClick","resposta: ${resposta}")
//                }
//                .launchIn(this)
//                .join()
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
          ) {
            Text(text = mensagem)
          }
          Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier
              .fillMaxWidth()
          ) {
            Text(text = resposta)
          }
  
        }
      }
    )
  }
}

