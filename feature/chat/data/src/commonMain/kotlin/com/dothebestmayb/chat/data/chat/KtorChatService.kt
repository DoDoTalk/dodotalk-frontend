package com.dothebestmayb.chat.data.chat

import com.dothebestmayb.chat.data.dto.ChatDto
import com.dothebestmayb.chat.data.dto.request.CreateChatRequest
import com.dothebestmayb.chat.data.mappers.toDomain
import com.dothebestmayb.chat.domain.chat.ChatService
import com.dothebestmayb.chat.domain.models.Chat
import com.dothebestmayb.core.data.networking.post
import com.dothebestmayb.core.domain.util.DataError
import com.dothebestmayb.core.domain.util.Result
import com.dothebestmayb.core.domain.util.map
import io.ktor.client.HttpClient

class KtorChatService(
    private val httpClient: HttpClient,
) : ChatService {
    override suspend fun createChat(otherUserIds: List<String>): Result<Chat, DataError.Remote> {
        return httpClient.post<CreateChatRequest, ChatDto>(
            route = "/chat",
            body = CreateChatRequest(
                otherUserIds = otherUserIds,
            )
        ).map { it.toDomain() }
    }
}
