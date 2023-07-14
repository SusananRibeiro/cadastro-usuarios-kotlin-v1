package com.controller.repository
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
class ConexaoDatabase {
    @get:Throws(SQLException::class) // Anotação para indicar que o getter pode lançar uma exceção SQLException
    @get:Synchronized // Anotação para tornar o getter sincronizado (thread-safe)
    val url = "jdbc:postgresql://localhost:5432/cadastro-usuarios" // URL do banco de dados
    val usuario = "postgres" // Usuário do banco de dados
    val senha = "postgres" // Senha do banco de dados
    var conexao: Connection? = null
        get() {
            if(field == null) {
                field = DriverManager.getConnection(url, usuario, senha) // conexão com o banco de dados
            }
            return field // Retorna o valor da conexao.
        }
        private set // Define o setter da conexao como privado para que a conexão só possa ser definida dentro da própria classe.
}

/*
// Configurações de conexão com o banco de dados
val url = "jdbc:postgresql://localhost:5432/cadastro-usuarios" // URL do banco de dados
val user = "postgres" // Usuário do banco de dados
val password = "postgres" // Senha do banco de dados

var conexao: Connection? = null

try {
    Class.forName("org.postgresql.Driver") // Carregar o driver do PostgreSQL
    conexao = DriverManager.getConnection(url, user, password) // Estabelecer a conexão com o banco de dados

    // Operações no banco de dados...
    // Aqui você pode realizar consultas, inserções, atualizações, etc.

    conexao.close() // Fechar a conexão com o banco de dados

} catch (e: ClassNotFoundException) {
    e.printStackTrace()
} catch (e: SQLException) {
    e.printStackTrace()
} finally {

    conexao?.close() // Certificar-se de fechar a conexão mesmo em caso de exceção
}
}
 */