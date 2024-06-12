package org.dtcm.work.navigationcomposables

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.dtcm.work.introduction.IntroductionScreen
import org.dtcm.work.navigationroute.ScreenRoute.INTRODUCTION

fun NavGraphBuilder.introductionGraph(navController: NavController) {
    composable(INTRODUCTION) {
        IntroductionScreen(navController = navController)
    }
}