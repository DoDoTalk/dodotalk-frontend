package com.dothebestmayb.chat.data.mappers

import com.dothebestmayb.chat.data.dto.ChatDto
import com.dothebestmayb.chat.domain.models.Chat
import kotlin.time.Instant

fun ChatDto.toDomain(): Chat {
    return Chat(
        id = id,
        participants = participants.map { it.toDomain() },
        lastActivityAt = Instant.parse(lastActivityAt),
        lastMessage = lastMessage?.toDomain(),
    )
}
