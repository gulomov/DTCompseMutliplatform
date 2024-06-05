package di

import org.dtcm.work.domain.FetchNewsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.GetHomeScreenNewsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { FetchNewsFromFirebaseAndSaveUseCase() }
    single { GetHomeScreenNewsUseCase() }
}