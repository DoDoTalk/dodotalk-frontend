package com.dothebestmayb.chat.data.di

import com.dothebestmayb.chat.data.chat.KtorChatParticipantService
import com.dothebestmayb.chat.data.chat.KtorChatService
import com.dothebestmayb.chat.domain.chat.ChatParticipantService
import com.dothebestmayb.chat.domain.chat.ChatService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val chatDataModule = module {
    singleOf(::KtorChatParticipantService) bind ChatParticipantService::class
    singleOf(::KtorChatService) bind ChatService::class
}
