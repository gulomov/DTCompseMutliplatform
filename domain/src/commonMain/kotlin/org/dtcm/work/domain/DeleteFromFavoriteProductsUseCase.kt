package org.dtcm.work.domain

import org.dtcm.work.repository.FavoriteProductsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteFromFavoriteProductsUseCase : KoinComponent {
    private val favoriteProductRepository: FavoriteProductsRepository by inject()

    suspend operator fun invoke(productId: Int) =
        favoriteProductRepository.deleteFromFavoriteProducts(productId)
}