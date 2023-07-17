package com.repository
import java.sql.Connection
import java.sql.DriverManager

class ConexaoDatabase {

    private val urlBanco: String = "jdbc:postgresql://localhost:5432/cadastro-usuarios"
    private val usuarioBanco: String = "postgres"
    private val senhaBanco: String = "postgres"
    val conexaoDoDatabase: Connection? = DriverManager.getConnection(urlBanco, usuarioBanco, senhaBanco)

}
