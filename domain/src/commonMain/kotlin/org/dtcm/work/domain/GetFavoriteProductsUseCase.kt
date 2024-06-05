package org.dtcm.work.domain

import org.dtcm.work.repository.FavoriteProductsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetFavoriteProductsUseCase: KoinComponent {
    private val favoriteProductsRepository: FavoriteProductsRepository by inject()
    operator fun invoke() = favoriteProductsRepository.getFavorites()
}