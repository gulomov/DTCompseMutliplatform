package org.dtcm.work.home.domain.recommendations

import GenericProductItem
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.common.data.createSavedStateHandle
import org.dtcm.work.common.data.navigation.ScreenRoute
import org.dtcm.work.design.GRID_CELLS
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun RecommendationsDetail(
    brandName: String,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val savedStateHandle = remember { createSavedStateHandle(mapOf("brandName" to brandName)) }
    val viewModel: RecommendationsDetailViewModel = koinInject { parametersOf(savedStateHandle) }
    val uiState by viewModel.uiState.collectAsState()

    uiState.navigationRoute?.let { route ->
        navController.navigate(route)
        viewModel.resetNavigation()
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(GRID_CELLS),
        content = {
            items(uiState.products) { product ->
                GenericProductItem(
                    item = product,
                    onClick = {
                        viewModel.onProductClicked(product.id.toString())
                    },
                    handleSaveClick = {
                        viewModel.handleSaveClick(
                            product = product,
                            isProductSaved = it
                        )
                    },
                    productImagesList = product.images?.map { it.imageUrl } ?: emptyList(),
                    productPercentage = product.salePercentage.toString(),
                    title = product.title.toString(),
                    originalPrice = product.originalPrice.toString(),
                    priceOnSale = product.priceOnSale.toString(),
                    isFavorite = uiState.favoriteIds.contains(product.id)
                )
            }
        })
}