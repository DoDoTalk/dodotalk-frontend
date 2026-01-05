package com.dothebestmayb.auth.presentation.email_verify_resend

import androidx.compose.foundation.text.input.TextFieldState
import com.dothebestmayb.core.presentation.util.UiText

data class EmailVerifyResendState(
    val emailTextFieldState: TextFieldState = TextFieldState(),
    val canSubmit: Boolean = false,
    val isLoading: Boolean = false,
    val errorText: UiText? = null,
    val isEmailSentSuccessfully: Boolean = false,
)
