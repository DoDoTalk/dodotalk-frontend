package com.dothebestmayb.chat.presentation.model

import com.dothebestmayb.chat.domain.models.ChatMessage
import com.dothebestmayb.core.designsystem.components.avatar.ChatParticipantUi
import kotlin.time.Instant

data class ChatUi(
    val id: String,
    val localParticipant: ChatParticipantUi,
    val otherParticipants: List<ChatParticipantUi>,
    val lastMessage: ChatMessage?,
    val lastActivityAt: Instant? = null,
    val lastMessageSenderUsername: String?,
)
