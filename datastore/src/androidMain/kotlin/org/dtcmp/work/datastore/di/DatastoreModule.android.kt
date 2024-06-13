package org.dtcmp.work.datastore.di

import android.content.Context
import org.dtcmp.work.datastore.IntroductionSettings
import org.dtcmp.work.datastore.createDataStoreAndroid
import org.dtcmp.work.datastore.provideIntroductionDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val datastoreModule = module {
    single { createDataStoreAndroid(androidContext()) }
    single<IntroductionSettings> { provideIntroductionDataStore(androidContext()) }
}