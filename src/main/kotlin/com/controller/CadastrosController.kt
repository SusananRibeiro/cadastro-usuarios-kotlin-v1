package com.controller

import com.model.Cadastros
import com.controller.repository.service.CadastrosService
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component

@Component
@FxmlView("/cadastro.fxml") // para lincar com o arquivo "cadastro.fxml"
class CadastrosController {
    @FXML
    private lateinit var idUsuarios: TextField
    @FXML
    private lateinit var nome: TextField
    @FXML
    private lateinit var cpf: TextField
    @FXML
    private lateinit var endereco: TextField
    @FXML
    private lateinit var telefone: TextField

    fun executarSalvar() {
        val cadastro: Cadastros = Cadastros()
        cadastro.idUsuario = idUsuarios.text
        cadastro.nome = nome.text
        cadastro.cpf = cpf.text
        cadastro.endereco = endereco.text
        cadastro.telefone = telefone.text

        try {
            val alertaObrig = Alert(Alert.AlertType.ERROR)
            alertaObrig.title = "Campo obrigatório"
            val alertaInval = Alert(Alert.AlertType.ERROR)
            alertaInval.title = "Erro"
            if (nome.text.isEmpty()) {
                alertaObrig.headerText = "É obrigatório informar o nome!"
                alertaObrig.show() // precisa para mostrar a tela do alerta
            } else if (cpf.text.isEmpty()) {
                alertaObrig.headerText = "É obrigatório informar o CPF!"
                alertaObrig.show()
            } else {
                CadastrosService.inserirCadastro(cadastro)
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        this.limparCamposUsuario()

    }
    fun limparCamposUsuario() {
        idUsuarios.text = "" // zera o campo
        nome.text = ""
        cpf.text = ""
        endereco.text = ""
        telefone.text = ""
    }
}