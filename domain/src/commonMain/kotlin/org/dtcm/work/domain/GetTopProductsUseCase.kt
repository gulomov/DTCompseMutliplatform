package org.dtcm.work.domain

import org.dtcm.work.repository.HomeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetTopProductsUseCase : KoinComponent {
    private val homeRepository: HomeRepository by inject()
    operator fun invoke() = homeRepository.getTopProducts()
}