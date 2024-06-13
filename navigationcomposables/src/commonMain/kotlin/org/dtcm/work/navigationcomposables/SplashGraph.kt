package org.dtcm.work.navigationcomposables

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.dtcm.work.navigationroute.ScreenRoute.INTRO_SPLASH
import org.dtcm.work.splashscreen.SplashScreen

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(INTRO_SPLASH) {
        SplashScreen(navController)
    }
}
