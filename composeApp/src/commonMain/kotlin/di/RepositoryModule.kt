package di

import org.dtcm.work.repository.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { HomeRepository() }
}