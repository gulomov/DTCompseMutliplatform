package org.dtcm.work.repository

import Converters
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import org.dtcm.work.common.data.data.ProductDetailsData
import org.dtcm.work.database.AppDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductDetailsRepository(private val roomDb: AppDatabase) {
    fun getTopProductDetails(productId: String) =
        roomDb.productDao().getTopProductDetail(productId).map {
            ProductDetailsData(
                id = it.id,
                address = it.address,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
                sizes = Converters().toSizesList(it.sizes),
            )
        }

    fun getProductDetails(productId: String) = roomDb.productDao().getProductDetail(productId).map {
        ProductDetailsData(
            id = it.id,
            address = it.address,
            images = Converters().toImagesList(it.imageUrl),
            title = it.title,
            salePercentage = it.salePercentage,
            saleStartsDate = it.saleStartsDate,
            saleEndsDate = it.saleEndsDate,
            priceOnSale = it.priceOnSale,
            originalPrice = it.originalPrice,
            sizes = Converters().toSizesList(it.sizes),
        )
    }

    suspend fun isProductBooked(productId: Int): Boolean? {
        return roomDb.productDao().getBookedProducts()
            .map { products -> products.any { it.productId == productId } }
            .firstOrNull()
    }
}