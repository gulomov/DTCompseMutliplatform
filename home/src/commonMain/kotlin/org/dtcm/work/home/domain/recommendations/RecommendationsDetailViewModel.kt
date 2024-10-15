package org.dtcm.work.home.domain.recommendations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.AllProductsItem
import org.dtcm.work.common.data.navigation.ScreenRoute
import org.dtcm.work.domain.DeleteFromFavoriteProductsUseCase
import org.dtcm.work.domain.GetFavoriteProductsIdsUseCase
import org.dtcm.work.domain.GetProductsByBrandNameUseCase
import org.dtcm.work.domain.SaveToFavoriteProductUseCase

private const val BRAND_NAME = "brandName"

class RecommendationsDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getProductsByBrandNameUseCase: GetProductsByBrandNameUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase,
    private val getFavoriteProductsIdsUseCase: GetFavoriteProductsIdsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecommendationsUiState())
    val uiState: StateFlow<RecommendationsUiState> = _uiState

    private val navigationRoute = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            combine(
                flowOf(getProductsByBrandNameUseCase(checkNotNull(savedStateHandle[BRAND_NAME]))),
                flowOf(getFavoriteProductsIdsUseCase()),
                navigationRoute,
            ) { productsName, favoriteProductsIds, navigationRoute ->
                RecommendationsUiState(
                    products = productsName,
                    favoriteIds = favoriteProductsIds,
                    navigationRoute = navigationRoute
                )
            }.collect {
                _uiState.value = it
            }
        }
    }

    fun handleSaveClick(product: AllProductsItem, isProductSaved: Boolean) = viewModelScope.launch {
        if (isProductSaved) {
            saveToFavoriteProductUseCase(product.asFavoriteProduct())
        } else {
            product.id?.let {
                deleteFromFavoriteProductsUseCase(it)
            }
        }
    }

    fun onProductClicked(productId: String) {
        val route = ScreenRoute.PRODUCTION_DETAIL.replace(
            "{productId}",
            productId
        )
        navigationRoute.value = route
    }

    fun resetNavigation() {
        navigationRoute.value = null
    }
}