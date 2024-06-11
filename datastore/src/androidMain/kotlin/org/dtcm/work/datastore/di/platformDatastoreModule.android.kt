package org.dtcm.work.datastore.di

import org.dtcm.work.datastore.IntroductionSettings
import org.dtcm.work.datastore.createDataStoreAndroid
import org.dtcm.work.datastore.provideIntroductionDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDatastoreModule = module {
    single { createDataStoreAndroid(get()) }
    single<IntroductionSettings> { provideIntroductionDataStore(get()) }
}