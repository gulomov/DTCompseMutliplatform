package org.dtcm.work.common.data.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.dtcm.work.design.small100
import org.dtcm.work.design.small50
import org.dtcm.work.design.topProductsImageHeightSize
import org.dtcm.work.design.topProductsImageWidthSize

@Composable
fun ProductImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(topProductsImageWidthSize)
            .height(topProductsImageHeightSize)
            .padding(small50)
            .clip(RoundedCornerShape(small100)),
    )
}