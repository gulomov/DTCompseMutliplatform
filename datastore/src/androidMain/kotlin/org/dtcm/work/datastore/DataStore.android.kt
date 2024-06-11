package org.dtcm.work.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun provideIntroductionDataStore(context: Any?): IntroductionSettings {
    require(context is Context) { "Android context is required" }
    return IntroductionSettingsDataStore(context)
}

fun createDataStoreAndroid(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)