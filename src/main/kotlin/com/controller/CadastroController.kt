package com.controller
import com.controller.repository.ConexaoDatabase
import com.model.Cadastro
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import java.sql.SQLException
// @FxmlView("/cadastro.fxml") // para lincar com o arquivo "cadastro.fxml"
class CadastroController {
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
        val cadastro = Cadastro()
        cadastro.nome = nome.text
        cadastro.cpf = cpf.text
        cadastro.endereco = endereco.text
        cadastro.telefone = telefone.text

        try {
            if(idUsuario.text.isEmpty()) {
                mensagemIdUsuario.text = "É obrigatório informar o código do usuário!"
            } else if (buscarIdUsuarioPorCadastro(cadastro.idUsuario!!)) {
                mensagemIdUsuario.text = "Código do usuário " + idUsuario.text + " já existe."
            } else if (nome.text.isEmpty()) {
                mensagemNome.text = "É obrigatório informar o nome!"
            } else if (cpf.text.isEmpty()) {
                mensagemCpf.text = "É obrigatório informar o CPF!"
            } else {
                inserirCadastro(cadastro)
                limparMensagens()
                limparCamposUsuario()
            }

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuario()

    }
    // Inserir (INSERT)
    private fun inserirCadastro(cadastro: Cadastro) {
        try {
            val sql = "INSERT INTO cadastros (id_usuario, nome, cpf, endereco, telefone) " +
                    "VALUES ( ?, ?, ?, ?, ?)"
            val pre = conexao.conexaoDoDatabase?.prepareStatement(sql)
            pre?.setInt(1, cadastro.idUsuario!!.toInt())
            pre?.setString(2, cadastro.nome)
            pre?.setString(3, cadastro.cpf)
            pre?.setString(4, cadastro.endereco)
            pre?.setString(5, cadastro.telefone)
            pre?.execute()
            pre?.close()
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    // Validar código do usuário único
    private fun buscarIdUsuarioPorCadastro(idUsuario: String): Boolean {
        try {
            val conn = conexao.conexaoDoDatabase
            val selectSql = "SELECT id FROM cadastros WHERE id_usuario = '$idUsuario'"
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