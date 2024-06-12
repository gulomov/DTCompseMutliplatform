package org.dtcmp.work.datastore

interface IntroductionSettings {
    suspend fun wasIntroductionShown(key: String, defaultValue: Boolean = false): Boolean
    suspend fun setIntroductionShown(key: String, value: Boolean)
}