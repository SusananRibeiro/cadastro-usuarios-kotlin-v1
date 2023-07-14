package com.controller
import com.controller.repository.ConexaoDatabase
import com.model.Cadastro
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import net.rgielen.fxweaver.core.FxmlView
import java.sql.SQLException
@FxmlView("/cadastro.fxml") // para lincar com o arquivo "cadastro.fxml"
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

    private val conexao = ConexaoDatabase()

    fun executarSalvar() {
        val cadastro: Cadastro = Cadastro()
        cadastro.idUsuario = idUsuario.text
        cadastro.nome = nome.text
        cadastro.cpf = cpf.text
        cadastro.endereco = endereco.text
        cadastro.telefone = telefone.text

        try {
            val alertaObrig = Alert(Alert.AlertType.ERROR)
            alertaObrig.title = "Campo obrigatório"
            val alertaInval = Alert(Alert.AlertType.ERROR)
            alertaInval.title = "Erro"
            if(idUsuario.text.isEmpty()) {
                alertaObrig.headerText = "É obrigatório informar o código do usuário!"
                alertaObrig.show() // precisa para mostrar a tela do alerta
            } else if (buscarIdUsuarioPorCadastro(cadastro.idUsuario!!)) {
                val alert = Alert(Alert.AlertType.ERROR)
                alert.title = "Alerta"
                alert.headerText = "Código do usuário " + idUsuario.text + " já existe."
                alert.show() // precisa para mostrar a tela do alerta
            } else if (nome.text.isEmpty()) {
                    alertaObrig.headerText = "É obrigatório informar o nome!"
                    alertaObrig.show() // precisa para mostrar a tela do alerta
                } else if (cpf.text.isEmpty()) {
                    alertaObrig.headerText = "É obrigatório informar o CPF!"
                    alertaObrig.show()
                } else {
                    inserirCadastro(cadastro)
                }

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        this.limparCamposUsuario()

    }
    // Inserir Usuario (INSERT)
    fun inserirCadastro(cadastro: Cadastro) {
        try {
            val conn = conexao.conexao
            val sql = "INSERT INTO cadastros (id_usuario, nome, cpf, endereco, telefone) " +
                    "VALUES ( ?, ?, ?, ?, ?)"
            val pre = conn?.prepareStatement(sql)
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
    fun limparCamposUsuario() {
        idUsuario.text = "" // zera o campo
        nome.text = ""
        cpf.text = ""
        endereco.text = ""
        telefone.text = ""
    }

    // Validar código do usuário único
    fun buscarIdUsuarioPorCadastro(idUsuario: String): Boolean {
        try {
            val conn = conexao.conexao
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
}