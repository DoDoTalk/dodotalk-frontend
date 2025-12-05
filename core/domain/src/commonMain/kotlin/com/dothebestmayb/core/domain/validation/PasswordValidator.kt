package com.dothebestmayb.core.domain.validation

object PasswordValidator {

    private const val MIN_PASSWORD_LENGTH = 9
    private const val MAX_PASSWORD_LENGTH = 20

    fun validate(password: String): PasswordValidationState {
        return PasswordValidationState(
            hasMinLength = password.length >= MIN_PASSWORD_LENGTH && password.length <= MAX_PASSWORD_LENGTH,
            hasDigit = password.any { it.isDigit() },
            hasUppercase = password.any { it.isUpperCase() },
        )
    }
}
