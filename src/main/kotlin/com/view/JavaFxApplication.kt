package com.view
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class JavaFxApplication : Application() {
    // Método start() responsável pela inicialização da interface gráfica JavaFX
    @Throws(Exception::class)
    override fun start(estagio: Stage) {
        val carregarFxml = FXMLLoader()
        val urlFxml = javaClass.getResource("/main.fxml")
        carregarFxml.location = urlFxml

        val root = carregarFxml.load<Parent>() // Carregando o layout definido no arquivo FXML
        estagio.scene = Scene(root) // Criando uma cena com o layout e definindo-a no palco (stage)
        estagio.title = "Cadastro Usuário" // Configuração do título da janela
        estagio.show() // Exibição da janela
    }
}
