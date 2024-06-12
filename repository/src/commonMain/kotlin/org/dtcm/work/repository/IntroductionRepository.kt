package org.dtcm.work.repository

import org.dtcmp.work.datastore.IntroductionSettings
import org.koin.core.component.KoinComponent

class IntroductionRepository(private val dataStore: IntroductionSettings) {
    suspend fun saveIntroductionData(key: String, value: Boolean) {
        dataStore.setIntroductionShown(key, value)
    }

    suspend fun getIntroductionData(key: String, defaultValue: Boolean): Boolean {
        return dataStore.wasIntroductionShown(key, defaultValue)
    }
}