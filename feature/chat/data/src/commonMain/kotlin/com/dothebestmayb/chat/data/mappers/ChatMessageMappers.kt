package com.dothebestmayb.chat.data.mappers

import com.dothebestmayb.chat.data.dto.ChatMessageDto
import com.dothebestmayb.chat.domain.models.ChatMessage
import kotlin.time.Instant

fun ChatMessageDto.toDomain(): ChatMessage {
    return ChatMessage(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = Instant.parse(createdAt),
        senderId = senderId,
    )
}
