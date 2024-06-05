package org.dtcm.work.domain

import org.dtcm.work.repository.HomeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchTopProductsFromFirebaseAndSaveUseCase : KoinComponent {
    private val homeRepository: HomeRepository by inject()
    suspend operator fun invoke() = homeRepository.fetchAndSaveTopProductsFromFirebase()
}