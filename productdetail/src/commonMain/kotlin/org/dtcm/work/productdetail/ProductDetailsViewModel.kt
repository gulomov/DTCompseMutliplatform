package org.dtcm.work.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.ProductDetailsDataResponse
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

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState = _uiState.asStateFlow()

    private val productDetail = MutableStateFlow(ProductDetailsData())
    private val startBookingLogic = MutableStateFlow(false)
    private val isProductBooked = MutableStateFlow(false)
    private val isProductFavorite = MutableStateFlow<Boolean?>(null)

    init {
        getProductDetail(productId)
        isProductBooked(productId)

        viewModelScope.launch {
            combine(
                getTopProductsUseCase(),
                getProductDetailsUseCase(productId),
                isProductBooked,
                startBookingLogic,
                isProductFavorite
            ) { topProductsList, productDetails, isProductBooked, startBookingLogic,
                isProductFavorite ->
                with(productDetails) {
                    ProductDetailsUiState(
                        productDetails = ProductDetailsData(
                            id = id,
                            images = images,
                            title = title,
                            salePercentage = salePercentage,
                            saleStartsDate = saleStartsDate,
                            saleEndsDate = saleEndsDate,
                            address = address,
                            originalPrice = originalPrice,
                            priceOnSale = priceOnSale,
                            sizes = sizes,
                        ),
                        topProducts = topProductsList,
                        isProductInFavorites = isProductFavorite ?: isProductInFavoritesUseCase(
                            productId.toInt()
                        ),
                        isProductBooked = isProductBooked,
                        startBookingLogic = startBookingLogic
                    )
                }
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    private fun getProductDetail(productId: String) = viewModelScope.launch {
        getProductDetailsUseCase(productId).collect {
            with(it) {
                productDetail.value = ProductDetailsData(
                    id = id,
                    images = images,
                    title = title,
                    salePercentage = salePercentage,
                    saleStartsDate = saleStartsDate,
                )
            }
        }
    }

    private fun isProductBooked(productId: String) = viewModelScope.launch {
        isProductBooked.value = isProductBookedUseCase(productId.toInt()) == true
    }


    fun handleSaveProductToFavorites() = viewModelScope.launch {
        if (isProductFavorite.value == true) {
            isProductFavorite.value = false
            deleteFromFavoriteProducts(uiState.value.productDetails.id)
        } else {
            isProductFavorite.value = true
            saveProductToFavorites(uiState.value.productDetails)
        }
    }

    private fun saveProductToFavorites(productItem: ProductDetailsData) = viewModelScope.launch {
        with(productItem) {
            saveToFavoriteProductUseCase(
                ProductDetailsDataResponse(
                    id = id,
                    images = images,
                    title = title,
                    salePercentage = salePercentage,
                    saleStartsDate = saleStartsDate,
                    saleEndsDate = saleEndsDate,
                    address = address,
                    originalPrice = originalPrice,
                    priceOnSale = priceOnSale,
                    sizes = sizes
                ).asFavoriteProduct()
            )
        }
    }

    private fun deleteFromFavoriteProducts(productId: Int) = viewModelScope.launch {
        deleteFromFavoriteProductsUseCase(productId)
    }

    fun startBookingLogic() {
        startBookingLogic.value = true
    }

    fun checkIfProductBooked() {
        startBookingLogic.value = false
        isProductBooked(productId)
    }
}