package com.dothebestmayb.core.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended

enum class DoDoTalkButtonStyle {
    PRIMARY,
    DESTRUCTIVE_PRIMARY,
    SECONDARY,
    DESTRUCTIVE_SECONDARY,
    TEXT
}

@Composable
fun DoDoTalkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: DoDoTalkButtonStyle = DoDoTalkButtonStyle.PRIMARY,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    val colors = when (style) {
        DoDoTalkButtonStyle.PRIMARY -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.extended.disabledFill,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
        )

        DoDoTalkButtonStyle.DESTRUCTIVE_PRIMARY -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError,
            disabledContainerColor = MaterialTheme.colorScheme.extended.disabledFill,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
        )

        DoDoTalkButtonStyle.SECONDARY -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.extended.textSecondary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
        )

        DoDoTalkButtonStyle.DESTRUCTIVE_SECONDARY -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.error,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
        )

        DoDoTalkButtonStyle.TEXT -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled,
        )
    }
    val defaultBorderStroke = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.extended.disabledOutline
    )
    val border = when {
        style == DoDoTalkButtonStyle.PRIMARY && !enabled -> defaultBorderStroke
        style == DoDoTalkButtonStyle.SECONDARY -> defaultBorderStroke
        style == DoDoTalkButtonStyle.DESTRUCTIVE_PRIMARY && !enabled -> defaultBorderStroke
        style == DoDoTalkButtonStyle.DESTRUCTIVE_SECONDARY -> {
            val borderColor = if (enabled) {
                MaterialTheme.colorScheme.extended.destructiveSecondaryOutline
            } else {
                MaterialTheme.colorScheme.extended.disabledOutline
            }
            BorderStroke(
                width = 1.dp,
                color = borderColor
            )
        }

        else -> null
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = colors,
        border = border,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(15.dp),
                    strokeWidth = 1.5.dp,
                    color = Color.Black,
                )
                return@Box
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon?.invoke()
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview
@Composable
private fun DoDoTalkPrimaryButtonPreview() {
    DoDoTalkTheme {
        DoDoTalkButton(
            text = "Hello World!",
            onClick = {},
            style = DoDoTalkButtonStyle.PRIMARY,
        )
    }
}

@Preview
@Composable
private fun DoDoTalkSecondaryButtonPreview() {
    DoDoTalkTheme {
        DoDoTalkButton(
            text = "Hello World!",
            onClick = {},
            style = DoDoTalkButtonStyle.SECONDARY,
        )
    }
}

@Preview
@Composable
private fun DoDoTalkDestructivePrimaryButtonPreview() {
    DoDoTalkTheme {
        DoDoTalkButton(
            text = "Hello World!",
            onClick = {},
            style = DoDoTalkButtonStyle.DESTRUCTIVE_PRIMARY,
        )
    }
}

@Preview
@Composable
private fun DoDoTalkDestructiveSecondaryButtonPreview() {
    DoDoTalkTheme {
        DoDoTalkButton(
            text = "Hello World!",
            onClick = {},
            style = DoDoTalkButtonStyle.DESTRUCTIVE_SECONDARY,
        )
    }
}

@Preview
@Composable
private fun DoDoTalkTextButtonPreview() {
    DoDoTalkTheme {
        DoDoTalkButton(
            text = "Hello World!",
            onClick = {},
            style = DoDoTalkButtonStyle.TEXT,
        )
    }
}
