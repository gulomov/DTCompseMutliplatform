package org.dtcm.work.gallery.components

import GenericProductItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.dtcm.work.common.data.data.AllProductsItem
import org.dtcm.work.common.data.data.BrandsItem
import org.dtcm.work.common.data.ui.EmptyStateImage
import org.dtcm.work.common.data.ui.MainLazyVerticalGrid
import org.dtcm.work.design.normal100

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
        MainLazyVerticalGrid {
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
        }
    }
}