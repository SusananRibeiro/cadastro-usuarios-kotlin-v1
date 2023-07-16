package com.controller
import com.controller.repository.ConexaoDatabase
import com.model.Usuario
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.control.TextField

class UsuarioController {
    @FXML
    private lateinit var nomeUsuario: TextField
    @FXML
    private lateinit var senha: TextField
    @FXML
    private lateinit var mensagemUsuario: Label
    @FXML
    private lateinit var mensagemSenha: Label

    private val conexao = ConexaoDatabase()
    fun executarLoginUsuario() {
        val user = Usuario()
        user.nomeUsuario = nomeUsuario.text
        user.senha = senha.text
        try {
            if (nomeUsuario.text.isEmpty()) {
                mensagemUsuario.text = "É obrigatório informar o usuário!"
            } else if (!validarNomeDoUsuario(nomeUsuario.text.uppercase())) {
                mensagemUsuario.text = "Usuário inválido."
            } else if (senha.text.isEmpty()) {
                mensagemSenha.text = "É obrigatório informar a senha!"
            } else if (!validarSenhaDoUsuario(senha.text)) {
                mensagemSenha.text = "Senha inválida."
            } else {
                val alert = Alert(Alert.AlertType.INFORMATION)
                alert.title = "Sucesso!"
                alert.headerText = "Login realizado com sucesso."
                alert.show()
                limparCamposUsuario()
                limparMensagens()
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuario()
    }

    // Validar usuário único
    private fun validarNomeDoUsuario (usuario: String): Boolean {
        try {
            val selectSql = "SELECT id FROM usuarios WHERE usuario = '$usuario'" // precisa colocar entre aspas simples
            val statement = conexao.conexaoDoDatabase?.createStatement()
            val resultado = statement?.executeQuery(selectSql)
            if (resultado != null) {
                return resultado.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
    private fun validarSenhaDoUsuario (senha: String): Boolean {
        try {
            val selectSql = "SELECT id FROM usuarios WHERE senha = '$senha'" // precisa colocar entre aspas simples
            val statement = conexao.conexaoDoDatabase?.createStatement()
            val resultado = statement?.executeQuery(selectSql)
            if (resultado != null) {
                return resultado.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
    private fun limparCamposUsuario() {
        nomeUsuario.text = "" // zera o campo
        senha.text = ""
    }
    private fun limparMensagens() {
        mensagemUsuario.text = ""
        mensagemSenha.text = ""
    }

}