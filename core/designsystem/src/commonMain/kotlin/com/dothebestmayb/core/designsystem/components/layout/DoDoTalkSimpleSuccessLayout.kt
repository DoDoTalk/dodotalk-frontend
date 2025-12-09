package com.dothebestmayb.core.designsystem.components.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkSuccessIcon
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButtonStyle
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended

@Composable
fun DoDoTalkSimpleSuccessLayout(
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    primaryButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    secondaryButton: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -(25).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.extended.textPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.extended.textSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            primaryButton()

            if (secondaryButton != null) {
                Spacer(modifier = Modifier.height(8.dp))
                secondaryButton()
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun DoDoTalkSimpleSuccessLayoutPreview() {
    DoDoTalkTheme {
        DoDoTalkSimpleSuccessLayout(
            title = "Hello world!",
            description = "Test description",
            icon = {
                DoDoTalkSuccessIcon()
            },
            primaryButton = {
                DoDoTalkButton(
                    text = "Log In",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            },
            secondaryButton = {
                DoDoTalkButton(
                    text = "Resend Verification Email",
                    onClick = {},
                    style = DoDoTalkButtonStyle.SECONDARY,
                    modifier = Modifier
                        .fillMaxWidth(),
                    )
            }
        )
    }
}

@Preview()
@Composable
private fun DoDoTalkSimpleSuccessLayoutDarkPreview() {
    DoDoTalkTheme(darkTheme = true) {
        DoDoTalkSimpleSuccessLayout(
            title = "Hello world!",
            description = "Test description",
            icon = {
                DoDoTalkSuccessIcon()
            },
            primaryButton = {
                DoDoTalkButton(
                    text = "Log In",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            },
            secondaryButton = {
                DoDoTalkButton(
                    text = "Resend Verification Email",
                    onClick = {},
                    style = DoDoTalkButtonStyle.SECONDARY,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        )
    }
}
