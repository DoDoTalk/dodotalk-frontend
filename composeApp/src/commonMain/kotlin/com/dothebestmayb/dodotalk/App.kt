package com.dothebestmayb.dodotalk

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dothebestmayb.auth.presentation.register.RegisterRoot
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme

@Composable
@Preview
fun App() {
    DoDoTalkTheme {
        RegisterRoot()
    }
}
