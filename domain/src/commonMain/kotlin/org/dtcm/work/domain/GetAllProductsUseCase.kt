package org.dtcm.work.domain

import org.dtcm.work.repository.AllProductsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllProductsUseCase: KoinComponent {
    private val allProductsRepository: AllProductsRepository by inject()

    operator fun invoke() = allProductsRepository.getAllProducts()
}