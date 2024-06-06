package org.dtcm.work.navigationcomposables

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.dtcm.work.favorites.FavoritesScreen
import org.dtcm.work.gallery.GalleryScreen
import org.dtcm.work.home.domain.HomeScreen
import org.dtcm.work.home.domain.news.NewsDetailScreen
import org.dtcm.work.home.domain.recommendations.RecommendationsDetail
import org.dtcm.work.navigationroute.ScreenRoute.FAVORITE
import org.dtcm.work.navigationroute.ScreenRoute.GALLERY
import org.dtcm.work.navigationroute.ScreenRoute.HOME
import org.dtcm.work.navigationroute.ScreenRoute.NEWS_DETAILS
import org.dtcm.work.navigationroute.ScreenRoute.PRODUCTION_DETAIL
import org.dtcm.work.navigationroute.ScreenRoute.RECOMMENDATION_DETAILS
import org.dtcm.work.productdetail.ProductDetails

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(HOME) {
        HomeScreen(navController = navController)
    }
    composable(
        route = PRODUCTION_DETAIL,
        arguments = listOf(
            navArgument("productId") {
                type = NavType.StringType
            },
        ),
    ) {
        val productId = it.arguments?.getString("productId").orEmpty()
        ProductDetails(productId = productId, navController = navController)
    }
    composable(
        route = NEWS_DETAILS,
        arguments = listOf(
            navArgument("newsId") {
                type = NavType.IntType
            },
        ),
    ) {
        val newsId = it.arguments?.getInt("newsId") ?: 0
        NewsDetailScreen(newsId = newsId, navController = navController)
    }
    composable(
        route = RECOMMENDATION_DETAILS,
        arguments = listOf(
            navArgument("brandName") {
                type = NavType.StringType
            }
        )
    ) {
        val brandName = it.arguments?.getString("brandName").orEmpty()
        RecommendationsDetail(brandName = brandName, navController = navController)
    }
    composable(FAVORITE) {
        FavoritesScreen(navController = navController)
    }

    composable(
        route = GALLERY,
        arguments = listOf(
            navArgument("brand") {
                type = NavType.StringType
                nullable = true
                defaultValue = "all"
            },
        ),
    ) {
        GalleryScreen(navController = navController)
    }
}