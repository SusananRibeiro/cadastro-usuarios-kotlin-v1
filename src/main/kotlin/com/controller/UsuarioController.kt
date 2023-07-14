package com.controller
import com.controller.repository.ConexaoDatabase
import com.model.Usuario
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component
import java.sql.SQLException

@Component
@FxmlView("/main.fxml")
class UsuarioController {
    @FXML
    private lateinit var nomeUsuario: TextField
    @FXML
    private lateinit var senha: TextField

    private val conexao = ConexaoDatabase()

    fun executarCadastarUsuario() {
        val user: Usuario = Usuario()
        user.nomeUsuario = nomeUsuario.text
        user.senha = senha.text
        try {
            val alertaObrig = Alert(Alert.AlertType.ERROR)
            alertaObrig.title = "Campo obrigatório"
            val alertaInval = Alert(Alert.AlertType.ERROR)
            alertaInval.title = "Erro"
            // Colocar na classe controller para não aceitar letras no campo documento
            if (nomeUsuario.text.isEmpty()) {
                alertaObrig.headerText = "É obrigatório informar o usuário!"
                alertaObrig.show()
            } else if (senha.text.isEmpty()) {
                alertaObrig.headerText = "É obrigatório informar a senha!"
                alertaObrig.show()
            } else if (buscarNomeUsuarioPorUsuario(user.nomeUsuario!!)) { // "!!" não nulo ou usar o "Elvis ?: nulo"
                val alert = Alert(Alert.AlertType.ERROR)
                alert.title = "Alerta"
                alert.headerText = "Usuário " + nomeUsuario.text + " já existe."
                alert.show() // precisa para mostrar a tela do alerta
            } else {
                inserirUsuario(user)
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuario()
        abrirTelaDeCadastro()
    }

    // Inserir Usuario (INSERT)
    fun inserirUsuario(usuario: Usuario) {
        try {
            val conn = conexao.conexao
            val sql = "INSERT INTO usuarios (usuario, senha) VALUES ( ?, ?)"
            val pre = conn?.prepareStatement(sql)
            pre?.setString(1, usuario.nomeUsuario)
            pre?.setString(2, usuario.senha)
            pre?.execute()
            pre?.close()
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    // Validar nome de usuário único
    fun buscarNomeUsuarioPorUsuario (usuario: String): Boolean {
        try {
            val conn = conexao.conexao
            val selectSql = "SELECT id FROM usuarios WHERE usuario = '$usuario'" // precisa colocar entre aspas simples
            val sta = conn?.createStatement()
            val resultSet = sta?.executeQuery(selectSql)
            if (resultSet != null) {
                return resultSet.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
    fun limparCamposUsuario() {
        nomeUsuario.text = "" // zera o campo
        senha.text = ""
    }

    fun abrirTelaDeCadastro() {
        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = javaClass.getResource("/cadastro.fxml")
        val scene = Scene(fxmlLoader.load())
        val stage = Stage()
        stage.title = "Alterar"
        stage.scene = scene
        stage.show()
    }
}