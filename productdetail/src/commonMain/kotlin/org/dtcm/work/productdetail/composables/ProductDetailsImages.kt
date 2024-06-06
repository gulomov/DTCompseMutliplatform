package org.dtcm.work.productdetail.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.diploma.work.common.Res
import org.diploma.work.common.ic_bookmark
import org.diploma.work.common.ic_bookmark_border
import org.dtcm.work.database.data.ProductImages
import org.dtcm.work.design.IndicatorDots
import org.dtcm.work.design.MainHorizontalPager
import org.dtcm.work.design.ProductHorizontalPager
import org.dtcm.work.design.normal175
import org.dtcm.work.design.small100
import org.dtcm.work.design.small50
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductDetailsImages(
    productImages: List<ProductImages>,
    saveProductIntoFavoritesClicked: (Boolean) -> Unit,
    isProductSavedIntoFavorites: Boolean?
) {
    val pagerState = rememberPagerState(pageCount = {
        productImages.size
    })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var isProductSaved by remember { mutableStateOf(isProductSavedIntoFavorites ?: false) }
    println("isProductSaved: $isProductSaved and isProductSavedIntoFavoritesFlow: $isProductSavedIntoFavorites")
    LaunchedEffect(isProductSavedIntoFavorites) {
        isProductSaved = isProductSavedIntoFavorites ?: false
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        MainHorizontalPager(
            pagerState = pagerState,
            itemContent = {
                ProductHorizontalPager(productImages[it].imageUrl)
            },
        )
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
                    if (!isProductSaved) {
                        Res.drawable.ic_bookmark_border
                    } else {
                        Res.drawable.ic_bookmark
                    }
                ),
                contentDescription = "Favorite",
                modifier = Modifier
                    .clickable(onClick = {
                        isProductSaved = isProductSaved == false
                        saveProductIntoFavoritesClicked(isProductSaved)
                    })
            )
        }
        Surface(
            modifier =
            Modifier
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
