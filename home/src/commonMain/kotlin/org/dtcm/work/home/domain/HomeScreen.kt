package org.dtcm.work.home.domain

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.dtcm.work.design.normal100
import org.dtcm.work.home.domain.components.NewsInHome
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val news by viewModel.news.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    LazyColumn(
        modifier = modifier,
    ) {
        item {
            NewsInHome(news = news, navController = navController)
            Spacer(modifier = Modifier.height(normal100))
        }
        /*item {
            RecommendationsInHome(recommendations = recommendations, navController = navController)
            Spacer(modifier = Modifier.height(normal100))
        }
        item {
            TopProductsLazyRow(
                productList = topProducts,
                navController = navController,
            )
        }*/
    }

}