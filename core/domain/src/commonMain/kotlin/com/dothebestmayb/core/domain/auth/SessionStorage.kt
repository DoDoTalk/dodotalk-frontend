package com.dothebestmayb.core.domain.auth

import kotlinx.coroutines.flow.Flow

interface SessionStorage {
    fun observeAuthInfo(): Flow<AuthInfo?>
    suspend fun set(info: AuthInfo?)
}
