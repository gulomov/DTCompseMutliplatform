package di

import org.dtcm.work.database.di.databaseModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module = module { }): KoinApplication = startKoin {
    modules(
        appModule,
        databaseModule,
        firebaseModule,
        repositoryModule,
        domainModule,
        viewModelModule,
        provideHttpClientModule
    )
}
