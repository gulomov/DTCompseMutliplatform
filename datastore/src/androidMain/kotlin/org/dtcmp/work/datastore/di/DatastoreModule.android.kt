package org.dtcmp.work.datastore.di

import org.dtcmp.work.datastore.IntroductionSettings
import org.dtcmp.work.datastore.createDataStoreAndroid
import org.dtcmp.work.datastore.provideIntroductionDataStore
import org.koin.dsl.module

actual val datastoreModule = module {
    single { createDataStoreAndroid(get()) }
    single<IntroductionSettings> { provideIntroductionDataStore(get()) }
}