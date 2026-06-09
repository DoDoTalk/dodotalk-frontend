package com.dothebestmayb.chat.presentation.mappers

import com.dothebestmayb.chat.domain.models.Chat
import com.dothebestmayb.chat.presentation.model.ChatUi

fun Chat.toUi(localParticipantId: String): ChatUi {
    val (local, other) = participants.partition { it.userId == localParticipantId }
    return ChatUi(
        id = id,
        localParticipant = local.first().toUi(),
        otherParticipants = other.map { it.toUi() },
        lastMessage = lastMessage,
        lastActivityAt = lastActivityAt,
        lastMessageSenderUsername = participants
            .find { it.userId == lastMessage?.senderId }
            ?.username
    )
}
