package com.controller.repository.service
import com.model.Usuarios
import com.controller.repository.ConexaoDatabase
import java.sql.SQLException

object UsuariosService {
    private val conexao = ConexaoDatabase()

    // Inserir Usuario (INSERT)
    @JvmStatic
    fun inserirUsuario(usuario: Usuarios) {
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

    // Validar o documento único
    @JvmStatic
    fun buscarUsuarioByUsuario(usuario: String): Boolean {
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
}