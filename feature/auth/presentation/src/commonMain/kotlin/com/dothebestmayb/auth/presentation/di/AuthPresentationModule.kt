package com.dothebestmayb.auth.presentation.di

import com.dothebestmayb.auth.presentation.email_verification.EmailVerificationViewModel
import com.dothebestmayb.auth.presentation.login.LoginViewModel
import com.dothebestmayb.auth.presentation.register.RegisterViewModel
import com.dothebestmayb.auth.presentation.register_success.RegisterSuccessViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
    viewModelOf(::EmailVerificationViewModel)
    viewModelOf(::LoginViewModel)
}
