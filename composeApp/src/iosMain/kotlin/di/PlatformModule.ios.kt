package di

import database.AppDatabase
import database.getAppDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<AppDatabase> {
        getAppDatabase()
    }
}