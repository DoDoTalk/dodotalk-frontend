package com.dothebestmayb.auth.presentation.reset_password

sealed interface ResetPasswordAction {
    data object OnChangeClick: ResetPasswordAction
    data object OnTogglePasswordVisibilityClick: ResetPasswordAction
}
