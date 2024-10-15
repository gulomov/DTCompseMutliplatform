package org.dtcm.work.home.domain.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import org.dtcm.work.common.data.data.NewsItem
import org.dtcm.work.design.IndicatorDots
import org.dtcm.work.design.newsCarouselImageSize
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100
import org.dtcm.work.design.small150
import org.dtcm.work.home.Res
import org.dtcm.work.home.homeScreenNewsTitle
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun NewsInHome(
    news: List<NewsItem>,
    onNewsClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { news.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(small100)
    ) {
        Text(
            text = stringResource(Res.string.homeScreenNewsTitle),
            modifier = Modifier.padding(start = small150, top = small150),
            fontWeight = FontWeight.Bold,
        )
        Card(
            modifier = Modifier
                .padding(horizontal = small150)
                .fillMaxWidth(),
            shape = RoundedCornerShape(normal100),
        ) {
            if (news.isNotEmpty()) {
                Box(modifier = modifier.fillMaxWidth()) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = modifier.fillMaxWidth()
                    ) { page ->
                        NewsItemComposable(news[page], onNewsItemClicked = onNewsClicked)
                    }
                    IndicatorDots(
                        modifier = Modifier
                            .padding(small100)
                            .align(Alignment.BottomCenter)
                            .background(shape = CircleShape, color = Color.Transparent),
                        totalDots = news.size,
                        selectedIndex = if (isDragged) pagerState.targetPage else pagerState.currentPage,
                        dotSize = small100,
                    )
                }
            }
        }
    }
}

@Composable
private fun NewsItemComposable(
    news: NewsItem,
    onNewsItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.clickable { onNewsItemClicked.invoke(news.id ?: 0) }
    ) {
        AsyncImage(
            model = news.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(newsCarouselImageSize)
                .align(Alignment.Center),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(normal100),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(small100)
        ) {
            news.body?.let { Text(text = it) }
            news.title?.let { Text(text = it) }
        }
    }
}