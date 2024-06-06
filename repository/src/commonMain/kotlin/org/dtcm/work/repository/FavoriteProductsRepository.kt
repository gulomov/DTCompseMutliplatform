package org.dtcm.work.repository

import Converters
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import org.dtcm.work.common.data.data.FavoriteProduct
import org.dtcm.work.database.AppDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavoriteProductsRepository(private val roomDb: AppDatabase) {

    suspend fun saveToFavoriteProduct(favoriteProduct: FavoriteProduct) =
        roomDb.productDao().saveToFavoriteProducts(favoriteProduct.asEntity())

    suspend fun deleteFromFavoriteProducts(productId: Int) =
        roomDb.productDao().deleteFromFavoriteProducts(productId)

    suspend fun isProductSavedToFavorites(productId: Int): Boolean =
        roomDb.productDao().getFavouriteProduct(productId).map {
            it?.id == productId
        }.firstOrNull() ?: false

    suspend fun getFavoriteProductsIds() = roomDb.productDao().getFavoriteProductIds()

    fun getFavorites() = roomDb.productDao().getFavouriteProducts().map {
        it.map { data ->
            FavoriteProduct(
                address = data.address,
                id = data.id,
                images = Converters().toImagesList(data.imageUrl),
                title = data.title,
                salePercentage = data.salePercentage,
                saleStartsDate = data.saleStartsDate,
                saleEndsDate = data.saleEndsDate,
                originalPrice = data.originalPrice,
                priceOnSale = data.priceOnSale,
                sizes = Converters().toSizesList(data.sizes)
            )
        }
    }
}