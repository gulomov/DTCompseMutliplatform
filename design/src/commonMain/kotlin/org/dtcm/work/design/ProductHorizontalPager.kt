package org.dtcm.work.design

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun ProductHorizontalPager(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model =imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.height(productsCarouselImageSize)
    )
}
