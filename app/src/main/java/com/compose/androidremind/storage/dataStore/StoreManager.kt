package com.compose.androidremind.storage.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreManager(private val ctx: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("store_sample")
        var STRING_DEMO_KEY = stringPreferencesKey("STRING_DEMO_KEY")
        var INT_DEMO_KEY = intPreferencesKey("INT_DEMO_KEY")
        var DOUBLE_DEMO_KEY = doublePreferencesKey("DOUBLE_DEMO_KEY")
    }

    suspend fun setString(str: String) {
        ctx.dataStore.edit { p ->
            p[STRING_DEMO_KEY] = str
        }
    }

    val getString: Flow<String?> = ctx.dataStore.data.map { p -> p[STRING_DEMO_KEY] }
}