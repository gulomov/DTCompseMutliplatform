package org.dtcm.work.home.domain.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.diploma.work.home.Res
import org.diploma.work.home.homeScreenNewsTitle
import org.dtcm.work.common.data.data.NewsItem
import org.dtcm.work.design.SlidingCarousel
import org.dtcm.work.design.newsCarouselImageSize
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100
import org.dtcm.work.navigationroute.ScreenRoute
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NewsInHome(
    news: List<NewsItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(Res.string.homeScreenNewsTitle),
            modifier = Modifier.padding(start = normal100, top = normal100),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(small100))
        Card(
            modifier = Modifier
                .padding(normal100)
                .fillMaxWidth(),
            shape = RoundedCornerShape(normal100),
        ) {
            news.let { newsInfo ->
                if (newsInfo.isNotEmpty()) {
                    SlidingCarousel(
                        itemsCount = newsInfo.size,
                        itemContent = {
                            NewsItem(news = newsInfo, index = it, newsItemClicked = { newsId ->
                                val route = ScreenRoute.NEWS_DETAILS.replace(
                                    "{newsId}", newsId.toString()
                                )
                                navController.navigate(route)
                            })
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun NewsItem(
    index: Int,
    news: List<NewsItem>,
    newsItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.clickable(onClick = {
        newsItemClicked.invoke(news[index].id ?: 0)
    })) {
        AsyncImage(
            model = news[index].image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
            Modifier.height(newsCarouselImageSize).align(Alignment.Center),
        )
        Column(
            modifier =
            Modifier
                .align(Alignment.BottomStart)
                .padding(normal100),
        ) {
            news[index].body?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
            Spacer(modifier = Modifier.width(small100))
            news[index].title?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
            Spacer(modifier = Modifier.width(small100))
        }
    }
}