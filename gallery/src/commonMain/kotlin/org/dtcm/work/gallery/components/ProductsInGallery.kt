package org.dtcm.work.gallery.components

import GenericProductItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.dtcm.work.common.data.data.AllProductsItem
import org.dtcm.work.common.data.data.BrandsItem
import org.dtcm.work.common.data.ui.EmptyStateImage
import org.dtcm.work.design.GRID_CELLS
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100


@Composable
fun ProductsInGallery(
    brands: List<BrandsItem>,
    products: List<AllProductsItem>,
    favoriteIds: List<Int>,
    onProductClicked: (Int) -> Unit,
    handleSaveClick: (Boolean, AllProductsItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (brands.isEmpty()) {
            EmptyStateImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(normal100)
                    .align(Alignment.Center)
            )
        }
        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Fixed(GRID_CELLS),
            contentPadding = PaddingValues(horizontal = normal100, vertical = small100),
            verticalArrangement = Arrangement.spacedBy(small100),
            horizontalArrangement = Arrangement.spacedBy(small100),
            content = {
                items(products) { product ->
                    GenericProductItem(
                        item = product,
                        onClick = { product.id?.let { onProductClicked(it) } },
                        handleSaveClick = { handleSaveClick(it, product) },
                        productImagesList = product.images?.map { it.imageUrl } ?: emptyList(),
                        productPercentage = product.salePercentage.toString(),
                        title = product.title.toString(),
                        originalPrice = product.originalPrice.toString(),
                        priceOnSale = product.priceOnSale.toString(),
                        isFavorite = favoriteIds.contains(product.id)
                    )
                }
            })
    }
}