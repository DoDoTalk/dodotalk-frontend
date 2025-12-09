package com.dothebestmayb.auth.presentation.register

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkBrandLogo
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButtonStyle
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkAdaptiveFormLayout
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkSnackbarScaffold
import com.dothebestmayb.core.designsystem.components.textfields.DoDoTalkPasswordTextField
import com.dothebestmayb.core.designsystem.components.textfields.DoDoTalkTextField
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.presentation.util.ObserveAsEvents
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.email
import dodotalk.feature.auth.presentation.generated.resources.email_placeholder
import dodotalk.feature.auth.presentation.generated.resources.login
import dodotalk.feature.auth.presentation.generated.resources.password
import dodotalk.feature.auth.presentation.generated.resources.password_hint
import dodotalk.feature.auth.presentation.generated.resources.register
import dodotalk.feature.auth.presentation.generated.resources.username
import dodotalk.feature.auth.presentation.generated.resources.username_hint
import dodotalk.feature.auth.presentation.generated.resources.username_placeholder
import dodotalk.feature.auth.presentation.generated.resources.welcome_to_dodotalk
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterEvent.Success -> {
                onRegisterSuccess(event.email)
            }
        }
    }

    RegisterScreen(
        state = state,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    DoDoTalkSnackbarScaffold(
        snackbarHostState = snackbarHostState
    ) {
        DoDoTalkAdaptiveFormLayout(
            headerText = stringResource(Res.string.welcome_to_dodotalk),
            errorText = state.registrationError?.asString(),
            logo = { DoDoTalkBrandLogo() },
        ) {
            DoDoTalkTextField(
                state = state.usernameTextState,
                placeholder = stringResource(Res.string.username_placeholder),
                title = stringResource(Res.string.username),
                supportingText = state.usernameError?.asString()
                    ?: stringResource(Res.string.username_hint),
                isError = state.usernameError != null,
                onFocusChanged = { isFocusChanged ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            DoDoTalkTextField(
                state = state.emailTextState,
                placeholder = stringResource(Res.string.email_placeholder),
                title = stringResource(Res.string.email),
                supportingText = state.emailError?.asString(),
                isError = state.emailError != null,
                onFocusChanged = { isFocusChanged ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            DoDoTalkPasswordTextField(
                state = state.passwordTextState,
                placeholder = stringResource(Res.string.password),
                title = stringResource(Res.string.password),
                supportingText = state.passwordError?.asString()
                    ?: stringResource(Res.string.password_hint),
                isError = state.passwordError != null,
                onFocusChanged = { isFocusChanged ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                },
                onToggleVisibilityClick = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                isPasswordVisible = state.isPasswordVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))

            DoDoTalkButton(
                text = stringResource(Res.string.register),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                },
                enabled = state.canRegister,
                isLoading = state.isRegistering,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))

            DoDoTalkButton(
                text = stringResource(Res.string.login),
                onClick = {
                    onAction(RegisterAction.OnLoginClick)
                },
                style = DoDoTalkButtonStyle.SECONDARY,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DoDoTalkTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}
