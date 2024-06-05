package org.dtcm.work.domain

import org.dtcm.work.repository.HomeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetNewsDetailUseCase : KoinComponent {
    private val homeRepository: HomeRepository by inject()
    operator fun invoke(id: Int) = homeRepository.getNewsDetail(id)
}