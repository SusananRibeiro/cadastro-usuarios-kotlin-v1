package com.controller
import com.controller.repository.ConexaoDatabase
import com.model.Pessoa
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import java.sql.SQLException
// @FxmlView("/cadastro.fxml") // para lincar com o arquivo "cadastro.fxml"
class PessoaController {
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
    private lateinit var mensagemIdUsuario: Label
    @FXML
    private lateinit var mensagemNome: Label
    @FXML
    private lateinit var mensagemCpf: Label

    private val conexao = ConexaoDatabase()

    fun executarSalvar() {
        val pessoa = Pessoa()
        pessoa.idUsuario = idUsuario.text
        pessoa.nome = nome.text
        pessoa.cpf = cpf.text
        pessoa.endereco = endereco.text
        pessoa.telefone = telefone.text

        try {
            if(idUsuario.text.isEmpty()) {
                mensagemIdUsuario.text = "É obrigatório informar o código do usuário!"
            } else if (buscarIdUsuarioPorCadastro(pessoa.idUsuario!!)) {
                mensagemIdUsuario.text = "Código do usuário " + idUsuario.text + " já existe."
            } else if (nome.text.isEmpty()) {
                mensagemNome.text = "É obrigatório informar o nome!"
            } else if (cpf.text.isEmpty()) {
                mensagemCpf.text = "É obrigatório informar o CPF!"
            } else {
                inserirCadastro(pessoa)
                limparMensagens()
                limparCamposUsuario()
            }

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuario()

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
    private fun buscarIdUsuarioPorCadastro(idUsuario: String): Boolean {
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
    private fun limparCamposUsuario() {
        idUsuario.text = "" // zera o campo
        nome.text = ""
        cpf.text = ""
        endereco.text = ""
        telefone.text = ""
    }
    private fun limparMensagens() {
        mensagemIdUsuario.text = ""
        mensagemNome.text = ""
        mensagemCpf.text = ""
    }

}