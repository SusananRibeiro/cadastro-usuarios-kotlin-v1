package com.view
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext

class JavaFxApplication : Application() {
    private var applicationContext: ConfigurableApplicationContext? = null
    // Método init() que é chamado antes do método start()
    override fun init() {
        val args = parameters.raw.toTypedArray()
        applicationContext = SpringApplicationBuilder() // Criação do ApplicationContext usando SpringApplicationBuilder
            .sources(UsuarioApp::class.java).run(*args) // Classe Main do projeto
    }

    // Método start() responsável pela inicialização da interface gráfica JavaFX
    @Throws(Exception::class)
    override fun start(stage: Stage) {
        val loader = FXMLLoader()
        val xmlUrl = javaClass.getResource("/main.fxml")
        loader.location = xmlUrl

        val root = loader.load<Parent>() // Carregando o layout definido no arquivo FXML
        stage.scene = Scene(root) // Criando uma cena com o layout e definindo-a no palco (stage)
        stage.title = "Cadastro Usuário" // Configuração do título da janela
        stage.show() // Exibição da janela
    }
}

fun main(args: Array<String>) {
    Application.launch(JavaFxApplication::class.java, *args)
}