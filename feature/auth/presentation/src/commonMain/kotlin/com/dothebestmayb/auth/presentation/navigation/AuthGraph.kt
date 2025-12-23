package com.dothebestmayb.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.dothebestmayb.auth.presentation.email_verification.EmailVerificationRoot
import com.dothebestmayb.auth.presentation.register.RegisterRoot
import com.dothebestmayb.auth.presentation.register_success.RegisterSuccessRoot

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onLoginSuccess: () -> Unit,
) {
    navigation<AuthGraphRoutes.Graph>(
        startDestination = AuthGraphRoutes.Register,
    ) {
        composable<AuthGraphRoutes.Register> {
            RegisterRoot(
                onRegisterSuccess = {
                    navController.navigate(AuthGraphRoutes.RegisterSuccess(it))
                }
            )
        }
        composable<AuthGraphRoutes.RegisterSuccess> {
            RegisterSuccessRoot()
        }
        composable<AuthGraphRoutes.EmailVerification>(
            deepLinks = listOf(
                navDeepLink {
                    this.uriPattern = "https://dodotalk.shop/api/auth/verify?token={token}"
                },
                navDeepLink {
                    this.uriPattern = "dodotalk://dodotalk.shop/api/auth/verify?token={token}"
                },
            )
        ) {
            EmailVerificationRoot()
        }
    }
}
