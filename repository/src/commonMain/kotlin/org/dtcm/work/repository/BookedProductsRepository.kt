package org.dtcm.work.repository

import org.dtcm.work.common.data.BookedProduct
import org.dtcm.work.database.AppDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookedProductsRepository: KoinComponent {
    private val roomDb: AppDatabase by inject()
    suspend fun saveBookedProduct(bookedProduct: BookedProduct) = roomDb.productDao().saveToBookedProducts(
        bookedProduct = bookedProduct.asEntity()
    )

    fun getBookedProduct(productId: Int) = roomDb.productDao().getBookedProductById(productId)
}