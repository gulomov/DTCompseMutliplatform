package org.dtcm.work.home.domain

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import org.dtcm.work.common.data.createSavedStateHandle
import org.dtcm.work.common.data.ui.TopProductsLazyRow
import org.dtcm.work.design.normal100
import org.dtcm.work.home.domain.components.NewsInHome
import org.dtcm.work.home.domain.components.RecommendationsInHome
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinInject(),
) {
    val news by viewModel.news.collectAsState()
    val recommendations by viewModel.recommendationsList.collectAsState()
    val topProducts by viewModel.topProductsList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
        viewModel.getRecommendationsList()
        viewModel.getTopProductsList()
    }

    LazyColumn(
        modifier = modifier,
    ) {
        item {
            NewsInHome(news = news, navController = navController)
            Spacer(modifier = Modifier.height(normal100))
        }
        item {
            RecommendationsInHome(recommendations = recommendations, navController = navController)
            Spacer(modifier = Modifier.height(normal100))
        }
        item {
            TopProductsLazyRow(productList = topProducts, navController = navController)
        }
    }

}