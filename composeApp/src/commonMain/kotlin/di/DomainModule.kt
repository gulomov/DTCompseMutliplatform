package di

import domain.FetchNewsFromFirebaseAndSaveUseCase
import org.koin.dsl.module

val domainModule = module {
    single { FetchNewsFromFirebaseAndSaveUseCase() }
}