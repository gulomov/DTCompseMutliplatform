package org.dtcm.work.favorites

import org.dtcm.work.common.data.data.FavoriteProduct

data class FavoritesUiState(
    val favoriteProducts: List<FavoriteProduct> = emptyList(),
    val navigationRoute: String? = null
)