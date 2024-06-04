package org.dtcm.work.database.di

import org.dtcm.work.database.AppDatabase
import org.dtcm.work.database.getAppDatabase
import org.koin.dsl.module

actual val databaseModule = module {
    single<AppDatabase> {
        getAppDatabase(get())
    }
}