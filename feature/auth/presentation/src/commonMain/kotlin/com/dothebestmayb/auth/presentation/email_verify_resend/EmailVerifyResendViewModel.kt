package com.dothebestmayb.auth.presentation.email_verify_resend

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dothebestmayb.auth.presentation.register_success.RegisterSuccessEvent
import com.dothebestmayb.core.domain.auth.AuthService
import com.dothebestmayb.core.domain.util.onFailure
import com.dothebestmayb.core.domain.util.onSuccess
import com.dothebestmayb.core.presentation.util.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmailVerifyResendViewModel(
    private val authService: AuthService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val email = savedStateHandle.get<String>("email") ?: ""

    private val _state = MutableStateFlow(
        EmailVerifyResendState(
            emailTextFieldState = TextFieldState(initialText = email)
        )
    )

    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EmailVerifyResendState()
        )

    fun onAction(action: EmailVerifyResendAction) {
        when (action) {
            is EmailVerifyResendAction.OnSubmitClick -> resendVerification()
        }
    }

    private fun resendVerification() {
        if (state.value.isLoading) {
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isEmailSentSuccessfully = false,
                    errorText = null,
                )
            }

            authService
                .resendVerificationEmail(email)
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isEmailSentSuccessfully = true,
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorText = error.toUiText(),
                        )
                    }
                }
        }
    }
}
