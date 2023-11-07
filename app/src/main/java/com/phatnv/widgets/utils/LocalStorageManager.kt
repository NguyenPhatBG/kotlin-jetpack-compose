package com.phatnv.widgets.utils
import android.content.Context
import android.util.Log
import androidx.datastore.core.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.preferencesDataStore as preferencesDataStore


class AppDataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "stores")
    suspend fun saveData(data: String, key: String) {
        val saveKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[saveKey] = data
        }
    }
    suspend fun getData(key: String): String {
        val keyGet = stringPreferencesKey(key)
        val result = context.dataStore.data.map { preferences ->
            preferences[keyGet] ?: ""
        }.first()
        return result
    }
    suspend fun deleteKey(key: String) {
        val keyDelete = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(keyDelete)
        }
    }

}
