package org.dtcm.work.favorites

import GenericProductItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.common.data.ui.EmptyStateImage
import org.dtcm.work.common.data.ui.MainLazyVerticalGrid
import org.dtcm.work.design.normal100
import org.koin.compose.koinInject

@Composable
fun FavoritesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsState()

    uiState.navigationRoute?.let {
        navController.navigate(it)
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (uiState.favoriteProducts.isEmpty()) {
            EmptyStateImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(normal100)
                    .align(Alignment.Center)
            )
        }
        MainLazyVerticalGrid {
            items(uiState.favoriteProducts) { favoriteProduct ->
                GenericProductItem(
                    item = favoriteProduct,
                    onClick = {
                        favoriteProduct.id?.let { id ->
                            viewModel.onProductClicked(id)
                        }
                    },
                    handleSaveClick = {
                        favoriteProduct.id?.let { id ->
                            viewModel.deleteFromFavoriteProducts(
                                id
                            )
                        }
                    },
                    productImagesList = favoriteProduct.images?.map { it.imageUrl }
                        ?: emptyList(),
                    productPercentage = favoriteProduct.salePercentage.toString(),
                    title = favoriteProduct.title.toString(),
                    originalPrice = favoriteProduct.originalPrice.toString(),
                    priceOnSale = favoriteProduct.priceOnSale.toString(),
                    modifier = Modifier,
                    isFavorite = true
                )
            }
        }
    }
}