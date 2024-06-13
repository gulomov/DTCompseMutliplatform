package org.dtcm.work.repository

import org.dtcmp.work.datastore.IntroductionSettings
import org.koin.core.component.KoinComponent

interface IntroductionRepository {
    suspend fun saveIntroductionData(key: String, value: Boolean)
    suspend fun wasIntroductionShown(key: String, defaultValue: Boolean = false): Boolean
}

class IntroductionRepositoryImpl(private val dataStore: IntroductionSettings) :
    IntroductionRepository {
    override suspend fun saveIntroductionData(key: String, value: Boolean) {
        dataStore.setIntroductionShown(key, value)
    }

    override suspend fun wasIntroductionShown(key: String, defaultValue: Boolean): Boolean {
        return dataStore.wasIntroductionShown(key, defaultValue)
    }
}