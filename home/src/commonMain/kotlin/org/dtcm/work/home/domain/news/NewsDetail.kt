package org.dtcm.work.home.domain.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.dtcm.work.common.data.createSavedStateHandle
import org.dtcm.work.design.newsCarouselImageSize
import org.dtcm.work.design.normal150
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun NewsDetailScreen(
    newsId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val savedStateHandle = remember { createSavedStateHandle(mapOf("newsId" to newsId)) }
    val viewModel: NewsDetailViewModel = koinInject { parametersOf(savedStateHandle) }
    val details by viewModel.details.collectAsState()

    Column(modifier = modifier) {
        AsyncImage(
            model = details.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(newsCarouselImageSize)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(normal150))
        Text(
            text = details.title.toString(),
            modifier = Modifier.padding(horizontal = normal150),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(normal150))
        Text(
            text = details.body.toString(),
            modifier = Modifier.padding(horizontal = normal150),
            fontWeight = FontWeight.Bold,
        )
    }
}