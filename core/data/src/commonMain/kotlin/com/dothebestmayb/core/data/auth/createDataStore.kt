package com.dothebestmayb.core.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

/**
 * @param producePath 각 플랫폼 별로 데이터를 저장할 위치를 구하는 방법이 다르므로 람다로 입력받음
 */
fun createDataStore(producePath: () -> String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath {
        producePath().toPath()
    }
}

internal const val DATA_STORE_FILE_NAME = "prefs.preferences_pb"
