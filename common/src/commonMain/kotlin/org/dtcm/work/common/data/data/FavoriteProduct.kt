package org.dtcm.work.common.data.data

import Converters
import kotlinx.serialization.Serializable
import org.dtcm.work.database.data.ProductImages
import org.dtcm.work.database.data.ProductSizes
import org.dtcm.work.database.entities.FavoriteProductsEntity

@Serializable
data class FavoriteProduct(
    val address: String? = null,
    val id: Int? = null,
    val images: List<ProductImages>? = null,
    val title: String? = null,
    val salePercentage: Int? = null,
    val saleStartsDate: String? = null,
    val saleEndsDate: String? = null,
    val originalPrice: Int? = null,
    val priceOnSale: Int? = null,
    val sizes: List<ProductSizes>? = null,
) {
    fun asEntity() = FavoriteProductsEntity(
        address = address.toString(),
        id = id ?: 0,
        imageUrl = Converters().fromImagesList(images ?: emptyList()),
        title = title.toString(),
        salePercentage = salePercentage ?: 0,
        saleStartsDate = saleStartsDate.toString(),
        saleEndsDate = saleEndsDate.toString(),
        originalPrice = originalPrice ?: 0,
        priceOnSale = priceOnSale ?: 0,
        sizes = Converters().fromSizesList(sizes ?: emptyList()),
    )
}

