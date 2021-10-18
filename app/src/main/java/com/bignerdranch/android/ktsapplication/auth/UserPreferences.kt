package com.bignerdranch.android.ktsapplication.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(
    context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
    private val dataStore: DataStore<Preferences> = context.dataStore

    fun observe(): Flow<Boolean> {
        return dataStore.data.map {
            it[KEY] == false
        }
    }

    suspend fun save(isLaunched: Boolean) {
        dataStore.edit {
            it[KEY] = isLaunched
        }
    }

    companion object {
        private const val DATASTORE_NAME = "datastore"
        private val KEY = booleanPreferencesKey("key")
    }
}