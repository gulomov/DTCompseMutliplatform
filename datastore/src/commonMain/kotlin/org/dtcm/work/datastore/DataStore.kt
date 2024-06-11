package org.dtcm.work.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

const val dataStoreFileName = "dt.work.kmp_pb"
const val introductionKey = "intro_key"
fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )


expect fun provideIntroductionDataStore(context: Any?): IntroductionSettings