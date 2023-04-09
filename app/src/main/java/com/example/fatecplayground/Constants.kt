package com.example.fatecplayground

enum class OpenAI(val url: String, val apiKey: String) {
  CHAT_COMPLETION(
    url = "https://api.openai.com/v1/chat/completions",
    apiKey = "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"
  )
}