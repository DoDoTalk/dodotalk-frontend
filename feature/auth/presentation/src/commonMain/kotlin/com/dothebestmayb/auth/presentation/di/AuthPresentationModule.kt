package com.dothebestmayb.auth.presentation.di

import com.dothebestmayb.auth.presentation.email_verification.EmailVerificationViewModel
import com.dothebestmayb.auth.presentation.forgot_password.ForgotPasswordViewModel
import com.dothebestmayb.auth.presentation.login.LoginViewModel
import com.dothebestmayb.auth.presentation.register.RegisterViewModel
import com.dothebestmayb.auth.presentation.register_success.RegisterSuccessViewModel
import com.dothebestmayb.auth.presentation.reset_password.ResetPasswordViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
    viewModelOf(::EmailVerificationViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::ForgotPasswordViewModel)
    viewModelOf(::ResetPasswordViewModel)
}
