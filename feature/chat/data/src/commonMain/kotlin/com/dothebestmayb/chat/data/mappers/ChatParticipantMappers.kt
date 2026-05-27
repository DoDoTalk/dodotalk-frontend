package com.dothebestmayb.chat.data.mappers

import com.dothebestmayb.chat.data.dto.ChatParticipantDto
import com.dothebestmayb.chat.domain.models.ChatParticipant

fun ChatParticipantDto.toDomain(): ChatParticipant {
    return ChatParticipant(
        userId = userId,
        username = username,
        profilePictureUrl = profilePictureUrl,
    )
}
