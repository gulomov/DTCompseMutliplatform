package org.dtcm.work.domain

import org.dtcm.work.common.data.FavoriteProduct
import org.dtcm.work.repository.FavoriteProductsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveToFavoriteProductUseCase : KoinComponent {
    private val productsRepository: FavoriteProductsRepository by inject()

    suspend operator fun invoke(favoriteProduct: FavoriteProduct) =
        productsRepository.saveToFavoriteProduct(favoriteProduct)
}