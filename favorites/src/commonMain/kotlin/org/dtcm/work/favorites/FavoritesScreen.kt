package org.dtcm.work.favorites

import GenericProductItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.common.data.navigation.ScreenRoute.PRODUCTION_DETAIL
import org.dtcm.work.common.data.ui.EmptyStateImage
import org.dtcm.work.design.GRID_CELLS
import org.dtcm.work.design.normal100
import org.koin.compose.koinInject

@Composable
fun FavoritesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = koinInject()
) {
    val favoriteProducts by viewModel.favoriteProducts.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        if (favoriteProducts.isEmpty()) {
            EmptyStateImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(normal100)
                    .align(Alignment.Center)
            )
        }
        LazyVerticalGrid(modifier = Modifier, columns = GridCells.Fixed(GRID_CELLS), content = {
            items(favoriteProducts) { favoriteProduct ->
                GenericProductItem(
                    item = favoriteProduct,
                    onClick = {
                        val route = PRODUCTION_DETAIL.replace("{productId}", it.id.toString())
                        navController.navigate(route)
                    },
                    onSaveOrDeleteClick = {
                        favoriteProduct.id?.let { id ->
                            viewModel.deleteFromFavoriteProducts(
                                id
                            )
                        }
                    },
                    productImagesList = favoriteProduct.images?.map { it.imageUrl } ?: emptyList(),
                    productPercentage = favoriteProduct.salePercentage.toString(),
                    title = favoriteProduct.title.toString(),
                    originalPrice = favoriteProduct.originalPrice.toString(),
                    priceOnSale = favoriteProduct.priceOnSale.toString(),
                    modifier = Modifier,
                    isFavorite = true
                )
            }
        })
    }
}