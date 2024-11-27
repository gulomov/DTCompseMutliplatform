package org.dtcm.work.productdetail

import org.dtcm.work.common.data.data.TopProductItem
import org.dtcm.work.database.data.ProductImages
import org.dtcm.work.database.data.ProductSizes

data class ProductDetailsUiState(
    val productDetails: ProductDetailsData = ProductDetailsData(),
    val topProducts: List<TopProductItem> = emptyList(),
    val isProductInFavorites: Boolean = false,
    val startBookingLogic: Boolean = false,
    val isProductBooked: Boolean = false,
)


data class ProductDetailsData(
    val id: Int = 0,
    val images: List<ProductImages> = emptyList(),
    val title: String = "",
    val salePercentage: Int = 0,
    val saleStartsDate: String = "",
    val saleEndsDate: String = "",
    val address: String = "",
    val originalPrice: Int = 0,
    val priceOnSale: Int = 0,
    val sizes: List<ProductSizes> = emptyList(),
)