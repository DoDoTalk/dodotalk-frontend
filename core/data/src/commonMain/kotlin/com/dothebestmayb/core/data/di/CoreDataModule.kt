package com.dothebestmayb.core.data.di

import com.dothebestmayb.core.data.auth.DataStoreSessionStorage
import com.dothebestmayb.core.data.auth.KtorAuthService
import com.dothebestmayb.core.data.logging.KermitLogger
import com.dothebestmayb.core.data.networking.HttpClientFactory
import com.dothebestmayb.core.domain.auth.AuthService
import com.dothebestmayb.core.domain.auth.SessionStorage
import com.dothebestmayb.core.domain.logging.DoDoTalkLogger
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformCoreDataModule: Module

val coreDataModule = module {
    includes(platformCoreDataModule)

    single<DoDoTalkLogger> { KermitLogger }

    single {
        HttpClientFactory(get(), get()).create(get())
    }

    // 아래 single block을 다르게 표현한 코드
    singleOf(::KtorAuthService) bind AuthService::class
//    single<AuthService> {
//        KtorAuthService(get())
//    }

    singleOf(::DataStoreSessionStorage) bind SessionStorage::class
}
