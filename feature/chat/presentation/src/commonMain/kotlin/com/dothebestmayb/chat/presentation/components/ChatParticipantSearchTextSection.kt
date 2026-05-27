package com.dothebestmayb.chat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButton
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkButtonStyle
import com.dothebestmayb.core.designsystem.components.textfields.DoDoTalkTextField
import com.dothebestmayb.core.presentation.util.UiText
import dodotalk.feature.chat.presentation.generated.resources.Res
import dodotalk.feature.chat.presentation.generated.resources.add
import dodotalk.feature.chat.presentation.generated.resources.email_or_username
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChatParticipantSearchTextSection(
    queryState: TextFieldState,
    onAddClick: () -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    isSearchEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    error: UiText? = null,
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp,
            ),
        verticalAlignment = Alignment.Top, // supportingText가 null이 아니여도 TextField가 세로의 중앙에 오도록 CenterVertically 대신 Top 사용
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DoDoTalkTextField(
            state = queryState,
            modifier = Modifier
                .weight(1f),
            placeholder = stringResource(Res.string.email_or_username),
            title = null,
            supportingText = error?.asString(),
            isError = error != null,
            singleLine = true,
            keyboardType = KeyboardType.Email,
            onFocusChanged = onFocusChanged,
        )
        DoDoTalkButton(
            text = stringResource(Res.string.add),
            onClick = onAddClick,
            style = DoDoTalkButtonStyle.SECONDARY,
            enabled = isSearchEnabled,
            isLoading = isLoading,
        )
    }
}
