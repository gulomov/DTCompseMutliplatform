package di

import domain.FetchNewsFromFirebaseAndSaveUseCase
import domain.GetHomeScreenNewsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { FetchNewsFromFirebaseAndSaveUseCase() }
    single { GetHomeScreenNewsUseCase() }
}