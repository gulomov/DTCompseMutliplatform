package org.dtcm.work.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.gallery.components.BrandsInGallery
import org.dtcm.work.gallery.components.ProductsInGallery
import org.koin.compose.koinInject

@Composable
fun GalleryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GalleryScreenViewModel = koinInject()
) {
    val uiState = viewModel.uiState.collectAsState()
    uiState.value.navigationRoute?.let {
        navController.navigate(it)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        BrandsInGallery(
            uiState.value.brandsList,
            onBrandClicked = viewModel::loadProductsByBrands
        )
        ProductsInGallery(
            brands = uiState.value.brandsList,
            products = uiState.value.productsList,
            favoriteIds = uiState.value.favoriteIds,
            onProductClicked = viewModel::onProductClicked,
            handleSaveClick = viewModel::handleSaveClick
        )
    }
}
