package com.dothebestmayb.core.domain.auth

import com.dothebestmayb.core.domain.util.DataError
import com.dothebestmayb.core.domain.util.EmptyResult

interface AuthService {

    suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote>

}
