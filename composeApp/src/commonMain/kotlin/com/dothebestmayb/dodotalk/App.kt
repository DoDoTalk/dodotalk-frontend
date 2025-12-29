package com.dothebestmayb.dodotalk

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dothebestmayb.auth.presentation.navigation.AuthGraphRoutes
import com.dothebestmayb.chat.presentation.chat_list.ChatListRoute
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.dodotalk.navigation.DeepLinkListener
import com.dothebestmayb.dodotalk.navigation.NavigationRoot
import org.koin.compose.viewmodel.koinViewModel

/**
 * @param onAuthenticationChecked Android에서 splashScreen을 그만 보여주는 시점을 결정하기 위한 변수
 */
@Composable
@Preview
fun App(
    onAuthenticationChecked: () -> Unit = {},
    viewModel: MainViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    DeepLinkListener(navController)

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isCheckingAuth) {
        if (!state.isCheckingAuth) {
            onAuthenticationChecked()
        }
    }
    DoDoTalkTheme {
        if (!state.isCheckingAuth) {
            NavigationRoot(
                navController = navController,
                startDestination = if (state.isLoggedIn) {
                    ChatListRoute
                } else {
                    AuthGraphRoutes.Graph
                },
            )
        }
    }
}
