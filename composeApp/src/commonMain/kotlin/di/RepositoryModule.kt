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
    factory { HomeRepository(get(), get()) }
    factory { ProductDetailsRepository(get()) }
    factory { AllProductsRepository(get(), get()) }
    factory { BookedProductsRepository(get()) }
    factory { FavoriteProductsRepository(get()) }
    factory<IntroductionRepository> { IntroductionRepositoryImpl(get()) }
}