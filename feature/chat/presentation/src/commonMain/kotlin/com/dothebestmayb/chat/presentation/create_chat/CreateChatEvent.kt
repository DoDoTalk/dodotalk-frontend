package com.dothebestmayb.chat.presentation.create_chat

import com.dothebestmayb.chat.domain.models.Chat

sealed interface CreateChatEvent {
    data class OnChatCreated(val chat: Chat): CreateChatEvent
}
