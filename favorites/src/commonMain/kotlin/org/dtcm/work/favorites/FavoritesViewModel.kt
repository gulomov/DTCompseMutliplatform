package org.dtcm.work.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.FavoriteProduct
import org.dtcm.work.common.data.navigation.ScreenRoute
import org.dtcm.work.domain.DeleteFromFavoriteProductsUseCase
import org.dtcm.work.domain.GetFavoriteProductsUseCase

private const val PRODUCT_ID = "{productId}"

class FavoritesViewModel(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState = _uiState.asStateFlow()

    private val navigationRoute = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            combine(
                getFavoriteProductsUseCase(),
                navigationRoute
            ) { favoriteProducts, navigationRoute ->
                FavoritesUiState(
                    favoriteProducts = favoriteProducts,
                    navigationRoute = navigationRoute
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    fun onProductClicked(id: Int) {
        val route = ScreenRoute.PRODUCTION_DETAIL.replace(
            PRODUCT_ID,
            id.toString()
        )
        navigationRoute.value = route
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }
}