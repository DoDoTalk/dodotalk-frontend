package com.dothebestmayb.chat.domain.chat

import com.dothebestmayb.chat.domain.models.Chat
import com.dothebestmayb.core.domain.util.DataError
import com.dothebestmayb.core.domain.util.Result

interface ChatService {
    suspend fun createChat(
        otherUserIds: List<String>
    ): Result<Chat, DataError.Remote>
}
