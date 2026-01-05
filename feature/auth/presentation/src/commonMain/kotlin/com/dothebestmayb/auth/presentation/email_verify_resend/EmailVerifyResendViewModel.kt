package com.dothebestmayb.auth.presentation.email_verify_resend

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dothebestmayb.auth.domain.EmailValidator
import com.dothebestmayb.core.domain.auth.AuthService
import com.dothebestmayb.core.domain.util.onFailure
import com.dothebestmayb.core.domain.util.onSuccess
import com.dothebestmayb.core.presentation.util.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmailVerifyResendViewModel(
    private val authService: AuthService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(
        EmailVerifyResendState(
            emailTextFieldState = TextFieldState(
                initialText = savedStateHandle.get<String>("email") ?: ""
            )
        )
    )

    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeValidationState()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EmailVerifyResendState()
        )

    private val isEmailValidFlow = snapshotFlow { state.value.emailTextFieldState.text.toString() }
        .map { email -> EmailValidator.validate(email) }
        .distinctUntilChanged()

    fun onAction(action: EmailVerifyResendAction) {
        when (action) {
            is EmailVerifyResendAction.OnSubmitClick -> resendVerification()
        }
    }

    private fun observeValidationState() {
        isEmailValidFlow.onEach { isEmailValid ->
            _state.update {
                it.copy(
                    canSubmit = isEmailValid
                )
            }
        }.launchIn(viewModelScope)
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
            val email = state.value.emailTextFieldState.text.toString()

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
