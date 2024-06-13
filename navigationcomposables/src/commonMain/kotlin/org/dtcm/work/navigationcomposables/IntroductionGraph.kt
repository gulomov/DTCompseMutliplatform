package org.dtcm.work.navigationcomposables

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.dtcm.work.common.data.navigation.ScreenRoute.INTRODUCTION
import org.dtcm.work.introduction.IntroductionScreen

fun NavGraphBuilder.introductionGraph(navController: NavController) {
    composable(INTRODUCTION) {
        IntroductionScreen(navController = navController)
    }
}