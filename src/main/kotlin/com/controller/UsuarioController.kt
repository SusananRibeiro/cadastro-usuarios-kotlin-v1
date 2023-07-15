package com.controller
import com.controller.repository.ConexaoDatabase
import com.model.Usuario
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.sql.SQLException
import java.sql.Statement

class UsuarioController {
    @FXML
    private lateinit var nomeUsuario: TextField
    @FXML
    private lateinit var senha: TextField
    @FXML
    private lateinit var mensagem: Label
    @FXML
    private lateinit var mensagemSenha: Label

    private val conexao = ConexaoDatabase()

    fun executarLoginUsuario() {
        val user: Usuario = Usuario()
        user.nomeUsuario = nomeUsuario.text
        user.senha = senha.text
        try {
            if (nomeUsuario.text.isEmpty()) {
                mensagem.text = "É obrigatório informar o usuário!"
            } else if (senha.text.isEmpty()) {
                mensagemSenha.text = "É obrigatório informar a senha!"
            } else if (buscarNomeUsuarioPorUsuario(user.nomeUsuario!!)) { // "!!" não nulo ou usar o "Elvis ?: nulo"
                mensagem.text = "Usuário " + nomeUsuario.text + " já existe."

            } else {
                inserirUsuario(user)
                limparMensagens()
                abrirTelaDeCadastro()
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuario()

    }

    // Inserir Usuario (INSERT)
    private fun inserirUsuario(usuario: Usuario) {
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
    private fun buscarNomeUsuarioPorUsuario (usuario: String): Boolean {
        try {
            val connection = conexao.conexao
            val selectSql = "SELECT id FROM usuarios WHERE usuario = '$usuario'" // precisa colocar entre aspas simples
            val statement: Statement? = connection?.createStatement() // Criar uma declaração SQL para a inserção de dados
            val resultSet = statement?.executeQuery(selectSql)
            if (resultSet != null) {
                return resultSet.next()
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
        mensagem.text = ""
        mensagemSenha.text = ""
    }

    private fun abrirTelaDeCadastro() {
        val carregarFxml = FXMLLoader()
        carregarFxml.location = javaClass.getResource("/cadastro.fxml")
        val cena = Scene(carregarFxml.load())
        val estagio = Stage()
        estagio.title = "Dados do Cadastro"
        estagio.scene = cena
        estagio.show()
    }

}