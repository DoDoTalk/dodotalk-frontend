package com.dothebestmayb.core.domain.validation

object UsernameValidator {

    private const val MIN_PASSWORD_LENGTH = 3
    private const val MAX_PASSWORD_LENGTH = 20

    fun validate(username: String): Boolean {
        return username.length in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH
    }
}
