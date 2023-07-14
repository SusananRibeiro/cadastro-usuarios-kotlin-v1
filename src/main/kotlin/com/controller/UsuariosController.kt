package com.controller
import com.model.Usuarios
import com.controller.repository.service.UsuariosService
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component
@Component
@FxmlView("/main.fxml")
class UsuariosController {
    @FXML
    private lateinit var nomeUsuario: TextField
    @FXML
    private lateinit var senha: TextField

    fun executarCadastarUsuario() {
        val user: Usuarios = Usuarios()
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
                alertaObrig.show() // precisa para mostrar a tela do alerta
            } else if (senha.text.isEmpty()) {
                alertaObrig.headerText = "É obrigatório informar a senha!"
                alertaObrig.show() // precisa para mostrar a tela do alerta
            } else if (UsuariosService.buscarUsuarioByUsuario(user.nomeUsuario!!)) { // "!!" não nulo ou usar o "Elvis ?: nulo"
                    val alert = Alert(Alert.AlertType.ERROR)
                    alert.title = "Alerta"
                    alert.headerText = "Usuário " + nomeUsuario.text + " já existe."
                    alert.show() // precisa para mostrar a tela do alerta
                } else {
                    UsuariosService.inserirUsuario(user)
                }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        limparCamposUsuario()

    }
    fun limparCamposUsuario() {
        nomeUsuario.text = "" // zera o campo
        senha.text = ""
    }
}