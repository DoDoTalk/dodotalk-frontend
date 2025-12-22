package com.dothebestmayb.auth.presentation.email_verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkFailureIcon
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkSuccessIcon
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButtonStyle
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkAdaptiveResultLayout
import com.dothebestmayb.core.designsystem.components.layout.DoDoTalkSimpleResultLayout
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended
import dodotalk.feature.auth.presentation.generated.resources.Res
import dodotalk.feature.auth.presentation.generated.resources.close
import dodotalk.feature.auth.presentation.generated.resources.email_verified_failed
import dodotalk.feature.auth.presentation.generated.resources.email_verified_failed_description
import dodotalk.feature.auth.presentation.generated.resources.email_verified_successfully
import dodotalk.feature.auth.presentation.generated.resources.email_verified_successfully_description
import dodotalk.feature.auth.presentation.generated.resources.login
import dodotalk.feature.auth.presentation.generated.resources.verifying_account
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EmailVerificationRoot(
    viewModel: EmailVerificationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EmailVerificationScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EmailVerificationScreen(
    state: EmailVerificationState,
    onAction: (EmailVerificationAction) -> Unit,
) {
    DoDoTalkAdaptiveResultLayout {
        when {
            state.isVerifying -> {
                VerifyingContent(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            state.isVerified -> {
                DoDoTalkSimpleResultLayout(
                    title = stringResource(Res.string.email_verified_successfully),
                    description = stringResource(Res.string.email_verified_successfully_description),
                    icon = {
                        DoDoTalkSuccessIcon()
                    },
                    primaryButton = {
                        DoDoTalkButton(
                            text = stringResource(Res.string.login),
                            onClick = {
                                onAction(EmailVerificationAction.OnLoginClick)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
            else -> {
                DoDoTalkSimpleResultLayout(
                    title = stringResource(Res.string.email_verified_failed),
                    description = stringResource(Res.string.email_verified_failed_description),
                    icon = {
                        Spacer(modifier = Modifier.height(32.dp))
                        DoDoTalkFailureIcon(
                            modifier = Modifier
                                .size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    },
                    primaryButton = {
                        DoDoTalkButton(
                            text = stringResource(Res.string.close),
                            onClick = {
                                onAction(EmailVerificationAction.OnCloseClick)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            style = DoDoTalkButtonStyle.SECONDARY
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun VerifyingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .heightIn(min = 200.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp),
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = stringResource(Res.string.verifying_account),
            color = MaterialTheme.colorScheme.extended.textSecondary,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview
@Composable
private fun EmailVerificationErrorPreview() {
    DoDoTalkTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun EmailVerificationVerifyingPreview() {
    DoDoTalkTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(
                isVerifying = true,
            ),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun EmailVerificationVerifiedPreview() {
    DoDoTalkTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(
                isVerified = true,
            ),
            onAction = {}
        )
    }
}
