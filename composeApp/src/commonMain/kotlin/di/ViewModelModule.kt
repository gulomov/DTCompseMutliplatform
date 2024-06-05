package di

import org.dtcm.work.home.domain.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel() }
}