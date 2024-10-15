package org.dtcm.work.home.domain.recommendations

import org.dtcm.work.common.data.data.AllProductsItem

data class RecommendationsUiState(
    val products: List<AllProductsItem> = emptyList(),
    val favoriteIds: List<Int> = emptyList(),
    val navigationRoute: String? = null,
)
