package com.dothebestmayb.chat.presentation.di

import com.dothebestmayb.chat.presentation.chat_list.ChatListViewModel
import com.dothebestmayb.chat.presentation.chat_list_detail.ChatListDetailViewModel
import com.dothebestmayb.chat.presentation.create_chat.CreateChatViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatPresentationModule = module {
    viewModelOf(::ChatListViewModel)
    viewModelOf(::ChatListDetailViewModel)
    viewModelOf(::CreateChatViewModel)
}
