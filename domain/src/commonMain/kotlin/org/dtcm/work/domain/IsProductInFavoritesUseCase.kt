package org.dtcm.work.domain

import org.dtcm.work.repository.FavoriteProductsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IsProductInFavoritesUseCase: KoinComponent {
    private val favoriteProductsRepository: FavoriteProductsRepository by inject()

    suspend operator fun invoke(productId: Int) =
        favoriteProductsRepository.isProductSavedToFavorites(productId)
}