package com.view
import javafx.application.Application

object UsuarioApp {
    @JvmStatic
    fun main(args: Array<String>) {
        Application.launch(JavaFxApplication::class.java, *args) // Chamada ao método launch da classe Application, iniciando a aplicação JavaFX e passando a classe
    }
}