package org.dtcm.work.home.domain

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.dtcm.work.design.small150
import org.dtcm.work.home.domain.components.NewsInHome
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinInject(),
) {
    val uiState = viewModel.uiState.collectAsState()

    uiState.value.navigationRoute?.let { route ->
        navController.navigate(route)
        viewModel.resetOnClick()
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(small150)
    ) {
        item {
            NewsInHome(
                news = uiState.value.newsInfo,
                onNewsClicked = viewModel::onNewsClicked
            )
        }
        /*item {
            RecommendationsInHome(
                recommendations = uiState.value.recommendationsList,
                navController = navController
            )
        }
        item {
            TopProductsLazyRow(
                productList = uiState.value.topProductsList,
                navController = navController
            )
        }*/
    }
}