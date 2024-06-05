package org.dtcm.work.home.domain.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.dtcm.work.design.newsCarouselImageSize
import org.dtcm.work.design.normal150
import org.koin.compose.koinInject

@Composable
fun NewsDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NewsDetailViewModel = koinInject()
) {
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