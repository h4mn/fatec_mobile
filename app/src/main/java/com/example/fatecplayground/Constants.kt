package com.example.fatecplayground

enum class OpenAI_Sample(val url: String, val apiKey: String) {
  CHAT_COMPLETION(
    url = "https://api.openai.com/v1/chat/completions",
    apiKey = "sk-123456789abcdef"
  )
}