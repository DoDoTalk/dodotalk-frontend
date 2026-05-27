package com.dothebestmayb.chat.presentation.mappers

import com.dothebestmayb.chat.domain.models.ChatParticipant
import com.dothebestmayb.core.designsystem.components.avatar.ChatParticipantUi

fun ChatParticipant.toUi(): ChatParticipantUi {
    return ChatParticipantUi(
        id = userId,
        username = username,
        initials = initials,
        imageUrl = profilePictureUrl,
    )
}
