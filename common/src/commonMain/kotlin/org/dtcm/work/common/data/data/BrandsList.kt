package org.dtcm.work.common.data.data

import kotlinx.serialization.Serializable

@Serializable
data class BrandsList(
    val brandsList: List<BrandsItem> = emptyList()
)

@Serializable
data class BrandsItem(
    val brandId: Int? = null,
    val imageUrl: String? = null,
    val brand: String? = null
)
