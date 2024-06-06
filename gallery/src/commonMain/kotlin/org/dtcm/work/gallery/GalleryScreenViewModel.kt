package org.dtcm.work.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.AllProductsItem
import org.dtcm.work.common.data.data.BrandsItem
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
    private val _brandsList = MutableStateFlow(listOf(BrandsItem()))
    val brandsList = _brandsList.asStateFlow()

    private val _products = MutableStateFlow(listOf(AllProductsItem()))
    val products: StateFlow<List<AllProductsItem>> = _products

    private val _favoriteIds = MutableStateFlow(listOf<Int>())
    val favoriteIds = _favoriteIds.asStateFlow()

    init {
        fetchAllProducts()
        fetchBrands()

    }

    fun getBrands() = viewModelScope.launch {
        getBrandsUseCase().collect {
            if (it.isNotEmpty()) {
                _brandsList.value = it
            } else {
                println("Brands is empty")
            }
        }
    }

    fun getAllProducts() = viewModelScope.launch {
        getAllProductsUseCase().collect {
            if (it.isNotEmpty()) {
                _products.value = it
            } else {
                println("Products list is empty")
            }
        }
    }

    fun loadProductsByBrands(brandName: String) = viewModelScope.launch {
        getProductsByBrandNameUseCase(brandName).apply {
            if (this.isEmpty()) {
                getAllProducts()
            } else {
                _products.value = this
            }
        }
        getFavoriteProductsIds()
    }

    fun getFavoriteProductsIds() {
        viewModelScope.launch {
            _favoriteIds.value = getFavoriteProductsIdsUseCase()
        }
    }

    private fun fetchAllProducts() = viewModelScope.launch {
        fetchAllProductsFromFirebaseAndSaveUseCase()
    }

    private fun fetchBrands() = viewModelScope.launch {
        fetchBrandsFromFirebaseAndSaveUseCase()
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun saveToFavoriteProduct(product: AllProductsItem) = viewModelScope.launch {
        saveToFavoriteProductUseCase(product.asFavoriteProduct())
    }

}