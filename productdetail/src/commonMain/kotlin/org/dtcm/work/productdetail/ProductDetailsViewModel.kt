package org.dtcm.work.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.ProductDetailsData
import org.dtcm.work.common.data.data.TopProductItem
import org.dtcm.work.domain.DeleteFromFavoriteProductsUseCase
import org.dtcm.work.domain.GetProductDetailsUseCase
import org.dtcm.work.domain.GetTopProductsUseCase
import org.dtcm.work.domain.IsProductBookedUseCase
import org.dtcm.work.domain.IsProductInFavoritesUseCase
import org.dtcm.work.domain.SaveToFavoriteProductUseCase

private const val PRODUCT_ID = "productId"

class ProductDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val isProductInFavoritesUseCase: IsProductInFavoritesUseCase,
    private val getTopProductsUseCase: GetTopProductsUseCase,
    private val saveToFavoriteProductUseCase: SaveToFavoriteProductUseCase,
    private val deleteFromFavoriteProductsUseCase: DeleteFromFavoriteProductsUseCase,
    private val isProductBookedUseCase: IsProductBookedUseCase,
) : ViewModel() {

    private val productId by lazy { checkNotNull(savedStateHandle.get<String>(PRODUCT_ID)) }
    private val _productDetail = MutableStateFlow(ProductDetailsData())
    val productDetails = _productDetail.asStateFlow()

    private val _topProductsList = MutableStateFlow(listOf<TopProductItem>())
    val topProductsList = _topProductsList.asStateFlow()

    private val _isProductInFavorites = MutableStateFlow<Boolean?>(null)
    val isProductInFavorites = _isProductInFavorites.asStateFlow()

    private val _startBookingLogic = MutableStateFlow(false)
    val startBookingLogic = _startBookingLogic.asStateFlow()

    private val _isProductBooked = MutableStateFlow(false)
    val isProductBooked = _isProductBooked.asStateFlow()

    init {
        getProductDetail(productId)
        checkProductIsFavorite(productId)
        isProductBooked(productId)
    }

    fun getTopProductsList() {
        viewModelScope.launch {
            getTopProductsUseCase().collect { data ->
                if (data.isNotEmpty()) {
                    _topProductsList.value = data
                } else {
                    println("Top products is empty")
                }
            }
        }
    }

    fun saveProductToFavorites(productItem: ProductDetailsData) = viewModelScope.launch {
        saveToFavoriteProductUseCase(productItem.asFavoriteProduct())
    }

    fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun startBookingLogic(shouldShowBookingLogic: Boolean) {
        _startBookingLogic.value = shouldShowBookingLogic
    }

    fun checkIfProductBooked() {
        _startBookingLogic.value = false
        isProductBooked(productId)
    }

    private fun getProductDetail(productId: String) = viewModelScope.launch {
        getProductDetailsUseCase(productId).collect {
            _productDetail.value = it
        }
    }

    private fun isProductBooked(productId: String) = viewModelScope.launch {
        _isProductBooked.value = isProductBookedUseCase(productId.toInt()) == true
    }

    private fun checkProductIsFavorite(productId: String) = viewModelScope.launch {
        _isProductInFavorites.value = isProductInFavoritesUseCase(productId.toInt())
    }

}