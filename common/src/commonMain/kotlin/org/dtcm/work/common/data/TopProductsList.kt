package org.dtcm.work.common.data

import kotlinx.serialization.Serializable
import org.dtcm.work.database.data.ProductImages
import org.dtcm.work.database.data.ProductSizes

@Serializable
data class TopProductsList(
    val topProductsList: List<TopProductItem>? = emptyList(),
)

@Serializable
data class TopProductItem(
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
    val brand: String? = null
)

