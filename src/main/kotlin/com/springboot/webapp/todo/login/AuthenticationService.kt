package com.springboot.webapp.todo.login

import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    fun authenticate(username: String, password: String): Boolean {
        val isValidUsername = username == "username"
        val isValidPassword = password == "password"

        return isValidUsername && isValidPassword
    }
}