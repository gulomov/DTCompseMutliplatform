package org.dtcm.work.common.data.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.dtcm.work.common.Res
import org.dtcm.work.common.ic_bookmark
import org.dtcm.work.common.ic_bookmark_border
import org.dtcm.work.common.productsSalePercentage
import org.dtcm.work.design.IndicatorDots
import org.dtcm.work.design.MainHorizontalPager
import org.dtcm.work.design.small100
import org.dtcm.work.design.small50
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenericProductImages(
    productPercentage: String,
    isFavorite: Boolean,
    imageUrls: List<String>,
    onSaveClick: (Boolean) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var isProductSaved by remember { mutableStateOf(isFavorite) }

    LaunchedEffect(isFavorite) {
        isProductSaved = isFavorite
    }

    Box {
        MainHorizontalPager(
            itemsCount = imageUrls.size,
            itemContent = { index ->
                ProductImage(imageUrl = imageUrls[index])
            }
        )
        Text(
            text = stringResource(
                Res.string.productsSalePercentage,
                productPercentage
            ),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = small50, end = small50)
                .background(
                    color = MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(topEnd = small100)
                )
                .padding(small50),
        )
        Image(
            painter = if (!isProductSaved) {
                painterResource(Res.drawable.ic_bookmark_border)
            } else {
                painterResource(Res.drawable.ic_bookmark)
            },
            contentDescription = "Favorite",
            modifier = Modifier
                .padding(end = small50, bottom = small50)
                .clickable(onClick = {
                    isProductSaved = isProductSaved == false
                    onSaveClick.invoke(isProductSaved)
                })
                .align(Alignment.BottomEnd)
        )
        Surface(
            modifier = Modifier
                .padding(small100)
                .align(Alignment.BottomCenter),
            shape = CircleShape
        ) {
            IndicatorDots(
                totalDots = imageUrls.size,
                selectedIndex = if (isDragged) {
                    pagerState.currentPage
                } else pagerState.targetPage,
                dotSize = small100
            )
        }
    }
}
