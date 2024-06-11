package org.dtcm.work.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class IntroductionSettingsDataStore(private val context: Context) : IntroductionSettings {

    private val Context.dataStore by preferencesDataStore(name = "settings")
    override suspend fun wasIntroductionShown(key: String, defaultValue: Boolean): Boolean {
        val dataStoreKey = booleanPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[dataStoreKey] ?: defaultValue
    }

    override suspend fun setIntroductionShown(key: String, value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }
}