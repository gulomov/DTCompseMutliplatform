package org.dtcmp.work.datastore

import platform.Foundation.NSUserDefaults

class IntroductionSettingsDataStore : IntroductionSettings {
    private val userDefaults = NSUserDefaults.standardUserDefaults
    override suspend fun wasIntroductionShown(key: String, defaultValue: Boolean): Boolean {
        return userDefaults.boolForKey(key) ?: defaultValue
    }

    override suspend fun setIntroductionShown(key: String, value: Boolean) {
        userDefaults.boolForKey(key)
    }
}