package com.controller.repository
import java.sql.Connection
import java.sql.DriverManager
class ConexaoDatabase {
    private val url: String = "jdbc:postgresql://localhost:5432/cadastro-usuarios"
    private val user: String = "postgres"
    private val senha: String = "postgres"
    val conexao: Connection? = DriverManager.getConnection(url, user, senha)


}
