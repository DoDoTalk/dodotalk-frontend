package com.dothebestmayb.chat.data.chat

import com.dothebestmayb.chat.data.dto.ChatParticipantDto
import com.dothebestmayb.chat.data.mappers.toDomain
import com.dothebestmayb.chat.domain.chat.ChatParticipantService
import com.dothebestmayb.chat.domain.models.ChatParticipant
import com.dothebestmayb.core.data.networking.get
import com.dothebestmayb.core.domain.util.DataError
import com.dothebestmayb.core.domain.util.Result
import com.dothebestmayb.core.domain.util.map
import io.ktor.client.HttpClient

class KtorChatParticipantService(
    private val httpClient: HttpClient
): ChatParticipantService {

    override suspend fun searchParticipant(query: String): Result<ChatParticipant, DataError.Remote> {
        return httpClient.get<ChatParticipantDto> (
            route = "/participants",
            queryParams = mapOf(
                "query" to query,
            )
        ).map { it.toDomain() }
    }
}
