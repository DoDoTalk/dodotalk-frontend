package com.dothebestmayb.auth.presentation.email_verify_resend

sealed interface EmailVerifyResendAction {
    data object OnSubmitClick: EmailVerifyResendAction
}
