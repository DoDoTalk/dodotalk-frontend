package com.dothebestmayb.auth.presentation.register_success

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkSuccessIcon
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButtonStyle
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkAdaptiveResultLayout
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkSimpleResultLayout
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkSnackbarScaffold
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.presentation.util.ObserveAsEvents
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.account_successfully_created
import dodotalk.feature.auth.presentation.generated.resources.login
import dodotalk.feature.auth.presentation.generated.resources.resend_verification_email
import dodotalk.feature.auth.presentation.generated.resources.resent_verification_email
import dodotalk.feature.auth.presentation.generated.resources.verification_email_send_to_x
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterSuccessRoot(
    viewModel: RegisterSuccessViewModel = koinViewModel(),
    onLoginClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterSuccessEvent.ResendVerificationEmailSuccess -> {
                snackbarHostState.showSnackbar(
                    message = getString(
                        resource = Res.string.resent_verification_email
                    )
                )
            }
        }
    }
    RegisterSuccessScreen(
        state = state,
        onAction = { action ->
            when (action) {
                RegisterSuccessAction.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
fun RegisterSuccessScreen(
    state: RegisterSuccessState,
    onAction: (RegisterSuccessAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    DoDoTalkSnackbarScaffold(
        snackbarHostState = snackbarHostState
    ) {
        DoDoTalkAdaptiveResultLayout {
            DoDoTalkSimpleResultLayout(
                title = stringResource(Res.string.account_successfully_created),
                description = stringResource(
                    Res.string.verification_email_send_to_x,
                    state.registerEmail
                ),
                icon = {
                    DoDoTalkSuccessIcon()
                },
                primaryButton = {
                    DoDoTalkButton(
                        text = stringResource(Res.string.login),
                        onClick = {
                            onAction(RegisterSuccessAction.OnLoginClick)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },
                secondaryButton = {
                    DoDoTalkButton(
                        text = stringResource(Res.string.resend_verification_email),
                        onClick = {
                            onAction(RegisterSuccessAction.OnResendVerificationEmailClick)
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = !state.isResendingVerificationEmail,
                        isLoading = state.isResendingVerificationEmail,
                        style = DoDoTalkButtonStyle.SECONDARY,
                    )
                },
                secondaryError = state.resendVerificationError?.asString(),
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DoDoTalkTheme {
        RegisterSuccessScreen(
            state = RegisterSuccessState(
                registerEmail = "test@dodotalk.com"
            ),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}
