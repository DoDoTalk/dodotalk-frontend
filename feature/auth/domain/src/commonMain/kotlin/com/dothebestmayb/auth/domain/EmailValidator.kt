package com.dothebestmayb.auth.domain

object EmailValidator {

    private const val EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    private val emailRegex = EMAIL_PATTERN.toRegex()

    fun validate(email: String): Boolean {
        return emailRegex.matches(email)
    }

}
