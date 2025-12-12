package com.dothebestmayb.dodotalk

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.dodotalk.navigation.NavigationRoot

@Composable
@Preview
fun App() {
    DoDoTalkTheme {
        NavigationRoot()
    }
}
