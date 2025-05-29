package org.dtcm.work.favorites

import org.dtcm.work.common.data.data.FavoriteProduct

// FIXME: Change data class into sealed class

data class FavoritesUiState(
    val favoriteProducts: List<FavoriteProduct> = emptyList(),
    val navigationRoute: String? = null
)