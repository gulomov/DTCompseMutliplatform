package org.dtcm.work.domain

import org.dtcm.work.repository.BookedProductsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetBookedProductByIdUseCase : KoinComponent {
    private val bookedProductsRepository: BookedProductsRepository by inject()
    operator fun invoke(productId: Int) = bookedProductsRepository.getBookedProduct(productId)
}