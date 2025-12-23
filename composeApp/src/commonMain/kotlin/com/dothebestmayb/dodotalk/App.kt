package com.dothebestmayb.dodotalk

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.dodotalk.navigation.DeepLinkListener
import com.dothebestmayb.dodotalk.navigation.NavigationRoot

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    DeepLinkListener(navController)

    DoDoTalkTheme {
        NavigationRoot(navController)
    }
}
