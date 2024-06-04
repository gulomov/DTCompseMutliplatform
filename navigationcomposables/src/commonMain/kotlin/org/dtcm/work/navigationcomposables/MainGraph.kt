package org.dtcm.work.navigationcomposables

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.dtcm.work.navigationroute.ScreenRoute.FAVORITE
import org.dtcm.work.navigationroute.ScreenRoute.GALLERY
import org.dtcm.work.navigationroute.ScreenRoute.HOME
import org.dtcm.work.navigationroute.ScreenRoute.NEWS_DETAILS
import org.dtcm.work.navigationroute.ScreenRoute.PRODUCTION_DETAIL
import org.dtcm.work.navigationroute.ScreenRoute.RECOMMENDATION_DETAILS

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(HOME) {
       // HomeScreen(navController = navController)
    }
    composable(
        route = PRODUCTION_DETAIL,
        arguments = listOf(
            navArgument("productId") {
                type = NavType.StringType
            },
        ),
    ) {
        //  ProductDetails(navController = navController)
    }
    composable(
        route = NEWS_DETAILS,
        arguments = listOf(
            navArgument("newsId") {
                type = NavType.IntType
            },
        ),
    ) {
        // NewsDetailScreen(navController = navController)
    }
    composable(
        route = RECOMMENDATION_DETAILS,
        arguments = listOf(
            navArgument("brandName") {
                type = NavType.StringType
            }
        )
    ) {
        //RecommendationsDetail(navController = navController)
    }
    composable(FAVORITE) {
        //   FavoritesScreen(navController = navController)
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
        //  GalleryScreen(navController = navController)
    }
}