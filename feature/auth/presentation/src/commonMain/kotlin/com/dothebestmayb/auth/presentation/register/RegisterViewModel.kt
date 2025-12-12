package com.dothebestmayb.auth.presentation.register

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dothebestmayb.auth.domain.EmailValidator
import com.dothebestmayb.core.domain.auth.AuthService
import com.dothebestmayb.core.domain.util.DataError
import com.dothebestmayb.core.domain.util.onFailure
import com.dothebestmayb.core.domain.util.onSuccess
import com.dothebestmayb.core.domain.validation.PasswordValidator
import com.dothebestmayb.core.domain.validation.UsernameValidator
import com.dothebestmayb.core.presentation.util.UitText
import com.dothebestmayb.core.presentation.util.toUiText
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.error_account_exists
import dodotalk.feature.auth.presentation.generated.resources.error_invalid_email
import dodotalk.feature.auth.presentation.generated.resources.error_invalid_password
import dodotalk.feature.auth.presentation.generated.resources.error_invalid_username
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authService: AuthService,
) : ViewModel() {

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeValidationStates()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RegisterState()
        )

    private val isEmailValidFlow = snapshotFlow { state.value.emailTextState.text.toString() }
        .map { email -> EmailValidator.validate(email) }
        .distinctUntilChanged()

    private val isUsernameValidFlow = snapshotFlow { state.value.usernameTextState.text.toString() }
        .map { username -> UsernameValidator.validate(username) }
        .distinctUntilChanged()

    private val isPasswordValidFlow = snapshotFlow { state.value.passwordTextState.text.toString() }
        .map { password -> PasswordValidator.validate(password).isValidPassword }
        .distinctUntilChanged()

    private val isRegisteringFlow = state
        .map { it.isRegistering }
        .distinctUntilChanged()

    private fun observeValidationStates() {
        combine(
            isEmailValidFlow,
            isUsernameValidFlow,
            isPasswordValidFlow,
            isRegisteringFlow,
        ) { isEmailValid, isUsernameValid, isPasswordValid, isRegistering ->
            val allValid = isEmailValid && isUsernameValid && isPasswordValid

            _state.update {
                it.copy(
                    canRegister = !isRegistering && allValid
                )
            }
        }.launchIn(viewModelScope)
    }


    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }

            else -> Unit
        }
    }

    private fun register() {
        if (!validateFormInputs()) {
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isRegistering = true,
                )
            }

            val email = state.value.emailTextState.text.toString()
            val username = state.value.usernameTextState.text.toString()
            val password = state.value.passwordTextState.text.toString()

            authService
                .register(
                    email = email,
                    username = username,
                    password = password
                )
                .onSuccess {
                    _state.update {
                        it.copy(
                            isRegistering = false,
                        )
                    }
                    eventChannel.send(RegisterEvent.Success(email))
                }
                .onFailure { error ->
                    val registrationError = when (error) {
                        DataError.Remote.CONFLICT -> UitText.Resource(Res.string.error_account_exists)
                        else -> error.toUiText()
                    }
                    _state.update {
                        it.copy(
                            isRegistering = false,
                            registrationError = registrationError
                        )
                    }
                }
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
