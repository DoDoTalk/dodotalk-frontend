package com.dothebestmayb.auth.presentation.register_success

import com.dothebestmayb.core.presentation.util.UiText

data class RegisterSuccessState(
    val registerEmail: String = "",
    val isResendingVerificationEmail: Boolean = false,
    val resendVerificationError: UiText? = null,
)
