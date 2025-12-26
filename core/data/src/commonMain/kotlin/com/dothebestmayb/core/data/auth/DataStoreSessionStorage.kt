package com.dothebestmayb.core.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dothebestmayb.core.data.mappers.toSerializable
import com.dothebestmayb.core.domain.auth.AuthInfo
import com.dothebestmayb.core.domain.auth.SessionStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class DataStoreSessionStorage(
    private val dataStore: DataStore<Preferences>
): SessionStorage {

    private val authInfoKey = stringPreferencesKey("Key_AUTH_INFO")

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override fun observeAuthInfo(): Flow<AuthInfo?> {
        return dataStore.data.map { preferences ->
            val serializedJson = preferences[authInfoKey]
            serializedJson?.let {
                json.decodeFromString(it)
            }
        }
    }

    override suspend fun set(info: AuthInfo?) {
        if (info == null) {
            dataStore.edit { prefs ->
                prefs.remove(authInfoKey)
            }
            return
        }

        val serialized = json.encodeToString(info.toSerializable())
        dataStore.edit { prefs ->
            prefs[authInfoKey] = serialized
        }
    }
}
