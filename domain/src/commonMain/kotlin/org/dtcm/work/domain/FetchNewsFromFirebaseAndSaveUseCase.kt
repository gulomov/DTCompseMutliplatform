package org.dtcm.work.domain

import org.dtcm.work.repository.HomeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchNewsFromFirebaseAndSaveUseCase(private val homeRepository: HomeRepository) {

    suspend operator fun invoke() = homeRepository.fetchAndSaveNewsInfoFromFirebase()
}