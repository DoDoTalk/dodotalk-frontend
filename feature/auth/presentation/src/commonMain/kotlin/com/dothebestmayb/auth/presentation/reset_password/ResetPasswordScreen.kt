package com.dothebestmayb.auth.presentation.reset_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.S
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkBrandLogo
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkAdaptiveFormLayout
import com.dothebestmayb.core.designsystem.components.textfields.DoDoTalkPasswordTextField
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.change
import dodotalk.feature.auth.presentation.generated.resources.password
import dodotalk.feature.auth.presentation.generated.resources.password_hint
import dodotalk.feature.auth.presentation.generated.resources.reset_password_successfully
import dodotalk.feature.auth.presentation.generated.resources.set_new_password
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResetPasswordRoot(
    viewModel: ResetPasswordViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ResetPasswordScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ResetPasswordScreen(
    state: ResetPasswordState,
    onAction: (ResetPasswordAction) -> Unit,
) {
    DoDoTalkAdaptiveFormLayout(
        headerText = stringResource(Res.string.set_new_password),
        errorText = state.errorText?.asString(),
        logo = {
            DoDoTalkBrandLogo()
        }
    ) {
        DoDoTalkPasswordTextField(
            state = state.passwordTextState,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = stringResource(Res.string.password),
            title = stringResource(Res.string.password),
            supportingText = stringResource(Res.string.password_hint),
            isPasswordVisible = state.isPasswordVisible,
            onToggleVisibilityClick = {
                onAction(ResetPasswordAction.OnTogglePasswordVisibilityClick)
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        DoDoTalkButton(
            text = stringResource(Res.string.change),
            onClick = {
                onAction(ResetPasswordAction.OnChangeClick)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading && state.canSubmit,
            isLoading = state.isLoading,
        )

        if (state.isResetSuccessful) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(Res.string.reset_password_successfully),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.extended.success,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DoDoTalkTheme {
        ResetPasswordScreen(
            state = ResetPasswordState(),
            onAction = {}
        )
    }
}
