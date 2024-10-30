package org.dtcm.work.gallery

import org.dtcm.work.common.data.data.AllProductsItem
import org.dtcm.work.common.data.data.BrandsItem

data class GalleryUiState(
    val brandsList: List<BrandsItem> = emptyList(),
    val productsList: List<AllProductsItem> = emptyList(),
    val favoriteIds: List<Int> = emptyList(),
    val navigationRoute: String? = null
)
