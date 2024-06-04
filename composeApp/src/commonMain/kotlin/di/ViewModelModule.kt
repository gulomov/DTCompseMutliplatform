package di

import home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel() }
}