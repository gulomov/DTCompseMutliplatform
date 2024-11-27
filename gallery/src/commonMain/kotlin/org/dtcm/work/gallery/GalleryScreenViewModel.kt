package org.dtcm.work.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.AllProductsItem
import org.dtcm.work.common.data.data.BrandsItem
import org.dtcm.work.common.data.navigation.ScreenRoute
import org.dtcm.work.domain.DeleteFromFavoriteProductsUseCase
import org.dtcm.work.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchBrandsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.GetAllProductsUseCase
import org.dtcm.work.domain.GetBrandsUseCase
import org.dtcm.work.domain.GetFavoriteProductsIdsUseCase
import org.dtcm.work.domain.GetProductsByBrandNameUseCase
import org.dtcm.work.domain.SaveToFavoriteProductUseCase

class GalleryScreenViewModel(
    private val fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase,
    private val fetchBrandsFromFirebaseAndSaveUseCase: FetchBrandsFromFirebaseAndSaveUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val getFavoriteProductsIdsUseCase: GetFavoriteProductsIdsUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductsByBrandNameUseCase: GetProductsByBrandNameUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState

    private val navigationRoute = MutableStateFlow<String?>(null)
    private val products = MutableStateFlow<List<AllProductsItem>>(emptyList())
    private val favoriteIds = MutableStateFlow<List<Int>>(emptyList())

    init {
        fetchAllProducts()
        fetchBrands()
        getFavoriteProductsIds()
        getAllProducts()
        viewModelScope.launch {
            combine(
                getBrandsUseCase(),
                products,
                favoriteIds,
                navigationRoute
            ) { brandsItems, allProductsItems, favoriteIds, navigationRoute ->
                GalleryUiState(
                    brandsList = brandsItems,
                    productsList = allProductsItems,
                    favoriteIds = favoriteIds,
                    navigationRoute = navigationRoute
                )
            }.collect {
                _uiState.value = it
            }
        }

    }

    fun loadProductsByBrands(brandName: String) = viewModelScope.launch {
        getProductsByBrandNameUseCase(brandName).apply {
            if (this.isEmpty()) {
                getAllProducts()
            } else {
                products.value = this
            }
        }
        getFavoriteProductsIds()
    }

    private fun getAllProducts() = viewModelScope.launch {
        getAllProductsUseCase().collect {
            if (it.isNotEmpty()) {
                products.value = it
            } else {
                println("Products list is empty")
            }
        }
    }

    private fun getFavoriteProductsIds() {
        viewModelScope.launch {
            favoriteIds.value = getFavoriteProductsIdsUseCase()
        }
    }

    private fun fetchAllProducts() = viewModelScope.launch {
        fetchAllProductsFromFirebaseAndSaveUseCase()
    }

    private fun fetchBrands() = viewModelScope.launch {
        fetchBrandsFromFirebaseAndSaveUseCase()
    }

    fun onProductClicked(id: Int) {
        val route = ScreenRoute.PRODUCTION_DETAIL.replace(
            "{productId}",
            id.toString()
        )
        navigationRoute.value = route
    }

    fun handleSaveClick(isSaved: Boolean, product: AllProductsItem) = viewModelScope.launch {
        if (!isSaved) {
            product.id?.let { deleteFromFavoriteProductsUseCase(it) }
        } else {
            saveToFavoriteProductUseCase(product.asFavoriteProduct())
        }

    }

}