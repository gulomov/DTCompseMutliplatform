package di

import org.dtcm.work.common.data.FavoriteProduct
import org.dtcm.work.repository.AllProductsRepository
import org.dtcm.work.repository.BookedProductsRepository
import org.dtcm.work.repository.HomeRepository
import org.dtcm.work.repository.IntroductionRepository
import org.dtcm.work.repository.ProductDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { HomeRepository(get(), get()) }
    factory { ProductDetailsRepository() }
    factory { AllProductsRepository() }
    factory { BookedProductsRepository() }
    factory { FavoriteProduct() }
    factory { IntroductionRepository() }
}