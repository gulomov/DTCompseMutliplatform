package domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.HomeRepository

class FetchNewsFromFirebaseAndSaveUseCase : KoinComponent {
    private val homeRepository: HomeRepository by inject()

    suspend operator fun invoke() = homeRepository.fetchAndSaveNewsInfoFromFirebase()
}