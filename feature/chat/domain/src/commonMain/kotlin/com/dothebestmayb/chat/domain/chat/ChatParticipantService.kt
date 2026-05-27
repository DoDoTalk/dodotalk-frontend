package com.dothebestmayb.chat.domain.chat

import com.dothebestmayb.chat.domain.models.ChatParticipant
import com.dothebestmayb.core.domain.util.DataError
import com.dothebestmayb.core.domain.util.Result

interface ChatParticipantService {
    suspend fun searchParticipant(
        query: String,
    ): Result<ChatParticipant, DataError.Remote>
}
