package org.dtcm.work.productdetail.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.dtcm.work.common.Res
import org.dtcm.work.common.ic_bookmark
import org.dtcm.work.common.ic_bookmark_border
import org.dtcm.work.database.data.ProductImages
import org.dtcm.work.design.IndicatorDots
import org.dtcm.work.design.ProductHorizontalPager
import org.dtcm.work.design.normal175
import org.dtcm.work.design.small100
import org.dtcm.work.design.small50
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductDetailsImages(
    productImages: List<ProductImages>,
    saveProductIntoFavoritesClicked: () -> Unit,
    isProductSavedIntoFavorites: Boolean
) {
    val pagerState = rememberPagerState(pageCount = { productImages.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(state = pagerState) {
            ProductHorizontalPager(productImages[it].imageUrl)
        }
        Box(
            Modifier
                .padding(
                    end = normal175,
                    bottom = small50
                )
                .align(Alignment.BottomEnd)
        ) {
            Image(
                painter = painterResource(
                    if (!isProductSavedIntoFavorites) {
                        Res.drawable.ic_bookmark_border
                    } else {
                        Res.drawable.ic_bookmark
                    }
                ),
                contentDescription = "Favorite",
                modifier = Modifier
                    .clickable(onClick = {
                        saveProductIntoFavoritesClicked()
                    })
            )
        }
        Surface(
            modifier = Modifier
                .padding(small100)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
        ) {
            IndicatorDots(
                totalDots = productImages.size,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = small100,
            )
        }
    }
}
