package com.dothebestmayb.core.designsystem.components.dialogs

import androidx.compose.runtime.Composable
import com.dothebestmayb.core.presentation.util.currentDeviceConfiguration

@Composable
fun DoDoTalkAdaptiveDialogSheetLayout(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    val configuration = currentDeviceConfiguration()
    if (configuration.isMobile) {
        DoDoTalkBottomSheet(
            onDismiss = onDismiss,
            content = content
        )
    } else {
        DoDoTalkDialogContent(
            onDismiss = onDismiss,
            content = content,
        )
    }
}
