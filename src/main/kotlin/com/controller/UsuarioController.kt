package com.controller
import com.controller.repository.ConexaoDatabase
import com.model.Pessoa
import com.model.Usuario
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.control.TextField
import java.sql.SQLException

class UsuarioController {
    @FXML
    private lateinit var nomeUsuario: TextField
    @FXML
    private lateinit var senha: TextField
    @FXML
    private lateinit var mensagemUsuario: Label
    @FXML
    private lateinit var mensagemSenha: Label
    @FXML
    private lateinit var idUsuario: TextField
    @FXML
    private lateinit var nome: TextField
    @FXML
    private lateinit var cpf: TextField
    @FXML
    private lateinit var endereco: TextField
    @FXML
    private lateinit var telefone: TextField
    @FXML
    private lateinit var mensagemCodigo: Label
    @FXML
    private lateinit var mensagemNome: Label
    @FXML
    private lateinit var mensagemCpf: Label

    private val conexao = ConexaoDatabase()
    fun executarBotaoLogin() {
        val user = Usuario()
        user.nomeUsuario = nomeUsuario.text
        user.senha = senha.text
        try {
            if (nomeUsuario.text.isEmpty()) {
                mensagemUsuario.text = "É obrigatório informar o usuário!"
            } else if (senha.text.isEmpty()) {
                mensagemSenha.text = "É obrigatório informar a senha!"
            } else if (!validarNomeDoUsuarioESenha(nomeUsuario.text, senha.text)) {
                mensagemUsuario.text = "Usuário ou senha inválido."
            } else {
                val alertaUsuario = Alert(Alert.AlertType.INFORMATION)
                alertaUsuario.title = "Sucesso!"
                alertaUsuario.headerText = "Login realizado com sucesso."
                alertaUsuario.show()
                limparCamposUsuario()
                limparMensagens()
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuario()

    }

    // Validar nome de usuário único
    private fun validarNomeDoUsuarioESenha (usuario: String, senha: String): Boolean {
        try {
            val selectSql = "SELECT id FROM usuarios WHERE usuario = '$usuario' AND senha = '$senha'" // precisa colocar entre aspas simples
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

    // PESSOA =====================================================================================
    fun executarBotaoSalvar() {
        val pessoa = Pessoa()
        pessoa.idUsuario = idUsuario.text
        pessoa.nome = nome.text
        pessoa.cpf = cpf.text
        pessoa.endereco = endereco.text
        pessoa.telefone = telefone.text

        try {
            if(idUsuario.text.isEmpty()) {
                mensagemCodigo.text = "É obrigatório informar o código do usuário!"
            } else if (validarIdUsuarioPorCadastro(pessoa.idUsuario!!)) {
                mensagemCodigo.text = "Código do usuário " + idUsuario.text + " já existe."
            } else if (nome.text.isEmpty()) {
                mensagemNome.text = "É obrigatório informar o nome!"
            } else if (cpf.text.isEmpty()) {
                mensagemCpf.text = "É obrigatório informar o CPF!"
            } else {
                inserirCadastro(pessoa)
                val alertaPessoa = Alert(Alert.AlertType.INFORMATION)
                alertaPessoa.title = "Sucesso!"
                alertaPessoa.headerText = "Pessoa cadastrada com sucesso, código " + (cpf.text.substring(0, 4))
                alertaPessoa.show()
                limparMensagensPessoa()
                limparCamposUsuarioPessoa()
            }

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuarioPessoa()

    }
    // Inserir (INSERT)
    private fun inserirCadastro(pessoa: Pessoa) {
        try {
            val sql = "INSERT INTO cadastros (id_usuario, nome, cpf, endereco, telefone) " +
                    "VALUES ( ?, ?, ?, ?, ?)"
            val preparedStatement = conexao.conexaoDoDatabase?.prepareStatement(sql)
            preparedStatement?.setInt(1, pessoa.idUsuario!!.toInt())
            preparedStatement?.setString(2, pessoa.nome)
            preparedStatement?.setString(3, pessoa.cpf)
            preparedStatement?.setString(4, pessoa.endereco)
            preparedStatement?.setString(5, pessoa.telefone)
            preparedStatement?.execute()
            preparedStatement?.close()
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    // Validar código do usuário único
    private fun validarIdUsuarioPorCadastro(idUsuario: String): Boolean {
        try {
            val selectSql = "SELECT id FROM cadastros WHERE id_usuario = '$idUsuario'"
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
    private fun limparCamposUsuarioPessoa() {
        idUsuario.text = "" // zera o campo
        nome.text = ""
        cpf.text = ""
        endereco.text = ""
        telefone.text = ""
    }
    private fun limparMensagensPessoa() {
        mensagemCodigo.text = ""
        mensagemNome.text = ""
        mensagemCpf.text = ""
    }

}