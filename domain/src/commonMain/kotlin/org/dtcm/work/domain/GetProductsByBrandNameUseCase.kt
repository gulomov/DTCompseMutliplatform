package org.dtcm.work.domain

import org.dtcm.work.repository.AllProductsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetProductsByBrandNameUseCase : KoinComponent {
    private val allProductsRepository: AllProductsRepository by inject()

    suspend operator fun invoke(brandName: String) =
        allProductsRepository.getProductsByBrandName(brandName)
}