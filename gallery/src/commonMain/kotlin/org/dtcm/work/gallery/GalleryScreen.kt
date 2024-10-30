package org.dtcm.work.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.design.normal100
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

    LaunchedEffect(Unit) {
        viewModel.getFavoriteProductsIds()
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        BrandsInGallery(uiState.value.brandsList, brandClick = {
            viewModel.loadProductsByBrands(brandName = it)
        })
        Spacer(modifier = Modifier.height(normal100))
        ProductsInGallery(
            brands = uiState.value.brandsList,
            products = uiState.value.productsList,
            favoriteIds = uiState.value.favoriteIds,
            onProductClicked = viewModel::onProductClicked,
            handleSaveClick = viewModel::handleSaveClick
        )
    }
}

