package org.dtcm.work.domain

import org.dtcm.work.repository.HomeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetNewsDetailUseCase(private val homeRepository: HomeRepository) {
    operator fun invoke(id: Int) = homeRepository.getNewsDetail(id)
}