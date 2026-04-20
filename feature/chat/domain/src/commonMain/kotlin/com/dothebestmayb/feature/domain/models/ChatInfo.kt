package com.dothebestmayb.feature.domain.models

data class ChatInfo(
    val chat: Chat,
    val messages: List<MessageWithSender>,
)
