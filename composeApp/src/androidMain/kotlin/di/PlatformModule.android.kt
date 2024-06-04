package di

import database.AppDatabase
import getAppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    single<AppDatabase> {
        getAppDatabase(get())
    }
}