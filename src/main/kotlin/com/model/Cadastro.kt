package com.model

class Cadastro {
    var idUsuario: String? = null
    var nome: String? = null
    var cpf: String? = null
    var endereco: String? = null
    var telefone: String? = null

    constructor()

    constructor(idUsuario: String?, nome: String?, cpf: String?, endereco: String?, telefone: String?) {
        this.idUsuario = idUsuario
        this.nome = nome
        this.cpf = cpf
        this.endereco = endereco
        this.telefone = telefone
    }
}