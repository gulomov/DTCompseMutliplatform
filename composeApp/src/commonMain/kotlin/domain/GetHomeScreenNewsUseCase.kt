package domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.HomeRepository

class GetHomeScreenNewsUseCase : KoinComponent {
    private val homeRepository: HomeRepository by inject()
    operator fun invoke() = homeRepository.getNewsInfo()
}