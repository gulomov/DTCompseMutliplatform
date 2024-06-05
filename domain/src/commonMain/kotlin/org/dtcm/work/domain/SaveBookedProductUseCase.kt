package org.dtcm.work.domain

import org.dtcm.work.common.data.BookedProduct
import org.dtcm.work.repository.BookedProductsRepository
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveBookedProductUseCase : KoinComponent {
    private val bookedProductsRepository: BookedProductsRepository by inject()
    suspend operator fun invoke(bookedProduct: BookedProduct) =
        bookedProductsRepository.saveBookedProduct(bookedProduct)
}