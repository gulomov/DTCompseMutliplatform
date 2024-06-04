package di

import org.koin.dsl.module
import repository.HomeRepository

val repositoryModule = module {
    factory { HomeRepository() }
}