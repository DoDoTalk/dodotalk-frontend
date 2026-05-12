package com.dothebestmayb.dodotalk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dothebestmayb.auth.presentation.navigation.AuthGraphRoutes
import com.dothebestmayb.auth.presentation.navigation.authGraph
import com.dothebestmayb.chat.presentation.navigation.ChatGraphRoutes
import com.dothebestmayb.chat.presentation.navigation.chatGraph

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Any,
    finish: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        authGraph(
            navController = navController,
            onLoginSuccess = {
                navController.navigate(ChatGraphRoutes.Graph) {
                    popUpTo(AuthGraphRoutes.Graph) {
                        inclusive = true
                    }
                }
            },
            finish = finish,
        )
        chatGraph(
            navController = navController,
        )
    }
}
