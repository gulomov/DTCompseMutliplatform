package org.dtcm.work.domain

import org.dtcm.work.repository.ProductDetailsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetTopProductDetailsUseCase : KoinComponent {
    private val productDetailsRepository: ProductDetailsRepository by inject()

    operator fun invoke(productId: String) =
        productDetailsRepository.getTopProductDetails(productId)
}