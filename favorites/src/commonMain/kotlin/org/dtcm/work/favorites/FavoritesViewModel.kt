package org.dtcm.work.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.FavoriteProduct
import org.dtcm.work.domain.DeleteFromFavoriteProductsUseCase
import org.dtcm.work.domain.GetFavoriteProductsUseCase

class FavoritesViewModel(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase
) : ViewModel() {
    private val _favoriteProducts = MutableStateFlow(listOf<FavoriteProduct>())
    val favoriteProducts = _favoriteProducts.asStateFlow()

    init {
        getFavoriteItems()
    }

    private fun getFavoriteItems() = viewModelScope.launch {
        getFavoriteProductsUseCase().collect {
            _favoriteProducts.value = it
        }
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }
}