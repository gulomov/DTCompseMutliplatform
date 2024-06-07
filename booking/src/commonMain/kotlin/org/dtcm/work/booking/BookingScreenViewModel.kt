package org.dtcm.work.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.BookedProduct
import org.dtcm.work.domain.GetBookedProductByIdUseCase
import org.dtcm.work.domain.SaveBookedProductUseCase

class BookingScreenViewModel(
    private val saveBookedProductUseCase: SaveBookedProductUseCase,
    private val getBookedProductById: GetBookedProductByIdUseCase,
) : ViewModel() {

    private val _bookedProductDate = MutableStateFlow<Long>(0)
    val bookedProductDate = _bookedProductDate.asStateFlow()

    fun saveBookedProduct(productId: Int, bookedDate: Long) = viewModelScope.launch {
        saveBookedProductUseCase(BookedProduct(productId, bookedDate))
    }

    fun getBookedProductDetail(productId: Int) = viewModelScope.launch {
        getBookedProductById(productId).firstOrNull()?.bookedDate.apply {
            _bookedProductDate.value = this ?: 0
        }
    }
}