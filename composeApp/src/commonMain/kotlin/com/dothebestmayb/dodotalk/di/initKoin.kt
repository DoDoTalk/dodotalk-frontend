package com.dothebestmayb.dodotalk.di

import com.dothebestmayb.auth.presentation.di.authPresentationModule
import com.dothebestmayb.chat.presentation.di.chatPresentationModule
import com.dothebestmayb.core.data.di.coreDataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            coreDataModule,
            authPresentationModule,
            appModule,
            chatPresentationModule,
        )
    }
}
