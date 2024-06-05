package di

import org.dtcm.work.domain.DeleteFromFavoriteProductsUseCase
import org.dtcm.work.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchBrandsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchNewsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchRecommendationsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchTopProductsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.GetAllProductsUseCase
import org.dtcm.work.domain.GetBookedProductByIdUseCase
import org.dtcm.work.domain.GetBrandsUseCase
import org.dtcm.work.domain.GetFavoriteProductsIdsUseCase
import org.dtcm.work.domain.GetFavoriteProductsUseCase
import org.dtcm.work.domain.GetHomeRecommendationsUseCase
import org.dtcm.work.domain.GetHomeScreenNewsUseCase
import org.dtcm.work.domain.GetNewsDetailUseCase
import org.dtcm.work.domain.GetProductDetailsUseCase
import org.dtcm.work.domain.GetProductsByBrandNameUseCase
import org.dtcm.work.domain.GetTopProductDetailsUseCase
import org.dtcm.work.domain.GetTopProductsUseCase
import org.dtcm.work.domain.IsProductBookedUseCase
import org.dtcm.work.domain.IsProductInFavoritesUseCase
import org.dtcm.work.domain.SaveBookedProductUseCase
import org.dtcm.work.domain.SaveToFavoriteProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchNewsFromFirebaseAndSaveUseCase(get()) }
    factory { FetchAllProductsFromFirebaseAndSaveUseCase() }
    factory { FetchRecommendationsFromFirebaseAndSaveUseCase() }
    factory { FetchTopProductsFromFirebaseAndSaveUseCase() }
    factory { FetchRecommendationsFromFirebaseAndSaveUseCase() }
    factory { FetchBrandsFromFirebaseAndSaveUseCase() }
    factory { GetHomeRecommendationsUseCase() }
    factory { GetNewsDetailUseCase(get()) }
    factory { GetHomeScreenNewsUseCase() }
    factory { GetBookedProductByIdUseCase() }
    factory { GetFavoriteProductsIdsUseCase() }
    factory { GetProductsByBrandNameUseCase() }
    factory { GetTopProductsUseCase() }
    factory { GetFavoriteProductsUseCase() }
    factory { GetAllProductsUseCase() }
    factory { GetBrandsUseCase() }
    factory { GetProductDetailsUseCase() }
    factory { GetTopProductDetailsUseCase() }
    factory { IsProductBookedUseCase() }
    factory { IsProductInFavoritesUseCase() }
    factory { SaveBookedProductUseCase() }
    factory { SaveToFavoriteProductUseCase() }
    factory { DeleteFromFavoriteProductsUseCase() }
}