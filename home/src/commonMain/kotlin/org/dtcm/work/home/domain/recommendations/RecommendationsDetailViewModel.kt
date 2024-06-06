package org.dtcm.work.home.domain.recommendations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.AllProductsItem
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
    private val _products = MutableStateFlow(listOf(AllProductsItem()))
    val products: StateFlow<List<AllProductsItem>> = _products

    private val _favoriteIds = MutableStateFlow(listOf<Int>())
    val favoriteIds = _favoriteIds.asStateFlow()

    init {
        viewModelScope.launch {
            _products.value =
                getProductsByBrandNameUseCase(checkNotNull(savedStateHandle[BRAND_NAME]))
            getFavoriteProductsIds()
        }
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun saveToFavoriteProduct(product: AllProductsItem) = viewModelScope.launch {
        saveToFavoriteProductUseCase(product.asFavoriteProduct())
    }

    fun getFavoriteProductsIds() {
        viewModelScope.launch {
            _favoriteIds.value = getFavoriteProductsIdsUseCase()
        }
    }
}