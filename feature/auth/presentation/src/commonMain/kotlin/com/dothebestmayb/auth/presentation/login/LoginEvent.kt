package com.dothebestmayb.auth.presentation.login

sealed interface LoginEvent {
    data object Success: LoginEvent

}
