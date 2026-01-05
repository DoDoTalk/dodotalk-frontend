package com.dothebestmayb.auth.presentation.email_verify_resend

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkBrandLogo
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkAdaptiveFormLayout
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkSnackbarScaffold
import com.dothebestmayb.core.designsystem.components.textfields.DoDoTalkTextField
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.email
import dodotalk.feature.auth.presentation.generated.resources.email_placeholder
import dodotalk.feature.auth.presentation.generated.resources.email_verify_resend
import dodotalk.feature.auth.presentation.generated.resources.resend_verification_email
import dodotalk.feature.auth.presentation.generated.resources.resent_verification_email
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EmailVerifyResendRoot(
    viewModel: EmailVerifyResendViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EmailVerifyResendScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EmailVerifyResendScreen(
    state: EmailVerifyResendState,
    onAction: (EmailVerifyResendAction) -> Unit,
) {
    DoDoTalkSnackbarScaffold {
        DoDoTalkAdaptiveFormLayout(
            headerText = stringResource(Res.string.email_verify_resend),
            errorText = state.errorText?.asString(),
            logo = {
                DoDoTalkBrandLogo()
            }
        ) {
            DoDoTalkTextField(
                state = state.emailTextFieldState,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = stringResource(Res.string.email_placeholder),
                title = stringResource(Res.string.email),
                isError = state.errorText != null,
                supportingText = state.errorText?.asString(),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoDoTalkButton(
                text = stringResource(Res.string.resend_verification_email),
                onClick = {
                    onAction(EmailVerifyResendAction.OnSubmitClick)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = !state.isLoading && state.canSubmit,
                isLoading = state.isLoading,
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (state.isEmailSentSuccessfully) {
                Text(
                    text = stringResource(Res.string.resent_verification_email),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.extended.success,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DoDoTalkTheme {
        EmailVerifyResendScreen(
            state = EmailVerifyResendState(),
            onAction = {}
        )
    }
}
