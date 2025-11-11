package com.dothebestmayb.core.presentation.util

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

sealed interface UitText {
    data class DynamicString(val value: String): UitText

    class Resource(
        val id: StringResource,
        val args: Array<Any> = arrayOf(),
    ): UitText

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is Resource -> stringResource(
                resource = id,
                *args,
            )
        }
    }

    suspend fun asStringAsync(): String {
        return when(this) {
            is DynamicString -> value
            is Resource -> getString(
                resource = id,
                *args,
            )
        }
    }
}
