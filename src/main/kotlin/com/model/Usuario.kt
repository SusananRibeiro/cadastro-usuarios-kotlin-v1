package com.model

class Usuario {
    var id: Int = 0
    var nomeUsuario: String? = null
    var senha: String? = null

    constructor()

    constructor(nomeUsuario: String?, senha: String?) {
        this.nomeUsuario = nomeUsuario
        this.senha = senha
    }
}