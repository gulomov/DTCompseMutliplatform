package org.dtcm.work.common.data.data

import kotlinx.serialization.Serializable
import org.dtcm.work.database.data.ProductImages
import org.dtcm.work.database.data.ProductSizes

@Serializable
data class ProductDetailsDataResponse(
    val id: Int,
    val images: List<ProductImages>,
    val title: String,
    val salePercentage: Int,
    val saleStartsDate: String,
    val saleEndsDate: String,
    val address: String,
    val originalPrice: Int,
    val priceOnSale: Int,
    val sizes: List<ProductSizes>,
) {
    fun asFavoriteProduct() = FavoriteProduct(
        address = address,
        id = id,
        images = images,
        title = title,
        salePercentage = salePercentage,
        saleStartsDate = saleStartsDate,
        saleEndsDate = saleEndsDate,
        originalPrice = originalPrice,
        priceOnSale = priceOnSale,
        sizes = sizes
    )
}
