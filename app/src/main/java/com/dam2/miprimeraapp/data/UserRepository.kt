package com.dam2.miprimeraapp.data

import com.dam2.miprimeraapp.model.User

class UserRepository {
    companion object {
        val repository = UserRepository()
    }

    private val usuarios: List<User> = listOf(
        User(
            "gerxodar",
            "123chocolateingles",
            "Germán Sánchez Casas",
            19,
            "662601388"
        ),

        User(
        "albasvlp",
        "987turronescoces",
        "Alba Sevilla López",
        18,
        "601432321"
        )
    )

    fun getUsers() = usuarios

    fun validateUsers(usernameinput: String, passwordinput: String) =
        usuarios.any {
            it.username == usernameinput &&
            it.password == passwordinput
        }

    fun getUser(usernameinput: String) = usuarios.first { it.username == usernameinput }
}