package com.dothebestmayb.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dothebestmayb.auth.domain.EmailValidator
import com.dothebestmayb.core.domain.validation.PasswordValidator
import com.dothebestmayb.core.domain.validation.UsernameValidator
import com.dothebestmayb.core.presentation.util.UitText
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.error_invalid_email
import dodotalk.feature.auth.presentation.generated.resources.error_invalid_password
import dodotalk.feature.auth.presentation.generated.resources.error_invalid_username
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
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
            initialValue = RegisterState()
        )

    fun onAction(action: RegisterAction) {
        when (action) {
            else -> Unit
        }
    }

    private fun clearAllTextFieldError() {
        _state.update {
            it.copy(
                emailError = null,
                usernameError = null,
                passwordError = null,
                registrationError = null,
            )
        }
    }

    private fun validateFormInputs(): Boolean {
        clearAllTextFieldError()

        val currentState = state.value
        val email = currentState.emailTextState.text.toString()
        val username = currentState.usernameTextState.text.toString()
        val password = currentState.passwordTextState.text.toString()

        val isEmailValid = EmailValidator.validate(email)
        val passwordValidationState = PasswordValidator.validate(password)
        val isUsernameValid = UsernameValidator.validate(username)

        val emailError = if (!isEmailValid) {
            UitText.Resource(Res.string.error_invalid_email)
        } else null
        val usernameError = if (!isUsernameValid) {
            UitText.Resource(Res.string.error_invalid_username)
        } else null
        val passwordError = if (!passwordValidationState.isValidPassword) {
            UitText.Resource(Res.string.error_invalid_password)
        } else null

        _state.update {
            it.copy(
                emailError = emailError,
                usernameError = usernameError,
                passwordError = passwordError,
            )
        }

        return isUsernameValid && isEmailValid && passwordValidationState.isValidPassword
    }
}
