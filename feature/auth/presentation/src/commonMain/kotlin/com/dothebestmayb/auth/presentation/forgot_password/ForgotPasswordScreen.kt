package com.dothebestmayb.auth.presentation.forgot_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkBrandLogo
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkAdaptiveFormLayout
import com.dothebestmayb.core.designsystem.components.textfields.DoDoTalkTextField
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.email
import dodotalk.feature.auth.presentation.generated.resources.email_placeholder
import dodotalk.feature.auth.presentation.generated.resources.forgot_password
import dodotalk.feature.auth.presentation.generated.resources.forgot_password_send_email
import org.jetbrains.compose.resources.stringResource

@Composable
fun ForgotPasswordRoot(
    viewModel: ForgotPasswordViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ForgotPasswordScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ForgotPasswordScreen(
    state: ForgotPasswordState,
    onAction: (ForgotPasswordAction) -> Unit,
) {
    DoDoTalkAdaptiveFormLayout(
        headerText = stringResource(Res.string.forgot_password),
        errorText = state.errorText?.asString(),
        logo = {
            DoDoTalkBrandLogo()
        }
    ) {
        DoDoTalkTextField(
            state = state.emailTextFiledState,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = stringResource(Res.string.email_placeholder),
            title = stringResource(Res.string.email),
            isError = state.emailError != null,
            supportingText = state.emailError?.asString(),
            keyboardType = KeyboardType.Email,
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        DoDoTalkButton(
            text = stringResource(Res.string.forgot_password_send_email),
            onClick = {
                onAction(ForgotPasswordAction.OnSubmitClick)
            },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = !state.isLoading && state.canSubmit,
            isLoading = state.isLoading,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DoDoTalkTheme {
        ForgotPasswordScreen(
            state = ForgotPasswordState(),
            onAction = {}
        )
    }
}
