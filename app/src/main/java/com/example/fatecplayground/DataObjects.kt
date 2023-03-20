package com.example.fatecplayground

/*data class Atividade(val Nome: String)

object DataAtividades {
  val listaDeAtividades = listOf(
    Atividade("Home"),
    Atividade("Projetos"),
    Atividade("Calculadora"),
  )
}*/

data class Projeto(
  val id: Int,
  val concluido: Boolean,
  val titulo: String,
  val description: String,
  val rota: String
)

object DataProjetos {
  val listaDeProjetos = listOf(
    Projeto(
      1, true, "Fatec Playground",
      // Descrição para a tela Fatec Playground:
      """
        |O Fatec Playground é um aplicativo que tem
        | como objetivo apresentar os projetos
        | desenvolvidos em sala de aula.
      """.trimMargin(),
      "Home"
    ),
    Projeto(
      2, true, "Calculadora",
      // Descrição para a tela Calculadora:
      """
        |A calculadora é um aplicativo que tem
        | como objetivo realizar operação
        | matemáticas básica de soma.
      """.trimMargin(),
      "Calculadora"
    ),
    Projeto(
      3, false, "Sorteio de #0 a #10",
      // Descrição para a tela Sorteio de #0 a #10:
      """
        |O sorteio de #0 a #10 é um aplicativo que
        | tem como objetivo sortear um número
        | aleatório entre 0 e 10.
      """.trimMargin(),
    "About"
    ),
  )
}