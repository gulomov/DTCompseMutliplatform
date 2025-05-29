package di

import org.dtcm.work.repository.AllProductsRepository
import org.dtcm.work.repository.BookedProductsRepository
import org.dtcm.work.repository.FavoriteProductsRepository
import org.dtcm.work.repository.HomeRepository
import org.dtcm.work.repository.IntroductionRepository
import org.dtcm.work.repository.IntroductionRepositoryImpl
import org.dtcm.work.repository.ProductDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get(), get()) }
    single { ProductDetailsRepository(get()) }
    single { AllProductsRepository(get(), get()) }
    single { BookedProductsRepository(get()) }
    single { FavoriteProductsRepository(get()) }
    single<IntroductionRepository> { IntroductionRepositoryImpl(get()) }
}