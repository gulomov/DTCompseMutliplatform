package di

import org.dtcm.work.common.data.FavoriteProduct
import org.dtcm.work.repository.AllProductsRepository
import org.dtcm.work.repository.BookedProductsRepository
import org.dtcm.work.repository.HomeRepository
import org.dtcm.work.repository.IntroductionRepository
import org.dtcm.work.repository.ProductDetailRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { HomeRepository() }
    factory { ProductDetailRepository() }
    factory { AllProductsRepository() }
    factory { BookedProductsRepository() }
    factory { FavoriteProduct() }
    factory { IntroductionRepository() }
}