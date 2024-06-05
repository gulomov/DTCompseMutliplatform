package org.dtcm.work.domain

import org.dtcm.work.repository.ProductDetailsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IsProductBookedUseCase : KoinComponent {
    private val productsDetailsRepository: ProductDetailsRepository by inject()

    suspend operator fun invoke(productId: Int) = productsDetailsRepository.isProductBooked(productId)
}