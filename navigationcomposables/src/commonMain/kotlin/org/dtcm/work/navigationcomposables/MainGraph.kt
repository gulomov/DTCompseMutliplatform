package org.dtcm.work.navigationcomposables

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.dtcm.work.common.data.navigation.ScreenRoute.FAVORITE
import org.dtcm.work.common.data.navigation.ScreenRoute.GALLERY
import org.dtcm.work.common.data.navigation.ScreenRoute.HOME
import org.dtcm.work.common.data.navigation.ScreenRoute.NEWS_DETAILS
import org.dtcm.work.common.data.navigation.ScreenRoute.PRODUCTION_DETAIL
import org.dtcm.work.common.data.navigation.ScreenRoute.RECOMMENDATION_DETAILS
import org.dtcm.work.favorites.FavoritesScreen
import org.dtcm.work.gallery.GalleryScreen
import org.dtcm.work.home.domain.HomeScreen
import org.dtcm.work.home.domain.news.NewsDetailScreen
import org.dtcm.work.home.domain.recommendations.RecommendationsDetail
import org.dtcm.work.productdetail.ProductDetails

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(
        HOME,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        }
    ) {
        HomeScreen(navController = navController)
    }
    composable(
        route = PRODUCTION_DETAIL,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
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
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
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
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        arguments = listOf(
            navArgument("brandName") {
                type = NavType.StringType
            }
        )
    ) {
        val brandName = it.arguments?.getString("brandName").orEmpty()
        RecommendationsDetail(brandName = brandName, navController = navController)
    }
    composable(
        FAVORITE,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
    ) {
        FavoritesScreen(navController = navController)
    }

    composable(
        route = GALLERY,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
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