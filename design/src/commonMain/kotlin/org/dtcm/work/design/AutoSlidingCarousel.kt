package org.dtcm.work.design

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

private const val NEXT_PAGE = 1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidingCarousel(
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000,
) {
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage(
            page = (pagerState.currentPage + NEXT_PAGE) % itemsCount,
            animationSpec = tween(5000), // FIXME: This should be reconsidered
        )
    }

    Box(modifier = modifier.fillMaxWidth()) {
        MainHorizontalPager(
            pagerState = pagerState,
            itemContent = itemContent,
        )

        Surface(
            modifier =
            Modifier
                .padding(small100)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
        ) {
            IndicatorDots(
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = small100,
            )
        }
    }
}