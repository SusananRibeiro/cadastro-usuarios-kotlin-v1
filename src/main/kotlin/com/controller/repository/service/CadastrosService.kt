package com.controller.repository.service
import com.model.Cadastros
import com.controller.repository.ConexaoDatabase
import java.sql.SQLException

object CadastrosService {
    private val conexao = ConexaoDatabase()

    // Inserir Usuario (INSERT)
    @JvmStatic
    fun inserirCadastro(cadastro: Cadastros) {
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
}