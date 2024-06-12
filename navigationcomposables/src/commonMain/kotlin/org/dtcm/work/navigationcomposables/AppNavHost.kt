package org.dtcm.work.navigationcomposables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.dtcm.work.navigationroute.ScreenRoute.FAVORITE
import org.dtcm.work.navigationroute.ScreenRoute.GALLERY
import org.dtcm.work.navigationroute.ScreenRoute.HOME
import org.dtcm.work.navigationroute.ScreenRoute.INTRODUCTION
import org.dtcm.work.navigationroute.ScreenRoute.INTRO_SPLASH
import org.dtcm.work.navigationroute.ScreenRoute.NEWS_DETAILS
import org.dtcm.work.navigationroute.ScreenRoute.PRODUCTION_DETAIL
import org.dtcm.work.navigationroute.ScreenRoute.RECOMMENDATION_DETAILS

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val bottomBarVisible = rememberSaveable { (mutableStateOf(true)) }
    val topBarVisibility = rememberSaveable { (mutableStateOf(true)) }
    val topBarBackArrowVisibility = rememberSaveable { (mutableStateOf(true)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        INTRO_SPLASH -> {
            topBarVisibility.value = false
            topBarBackArrowVisibility.value = false
            bottomBarVisible.value = false
        }

        INTRODUCTION -> {
            topBarVisibility.value = false
            bottomBarVisible.value = false
        }

        HOME -> {
            topBarVisibility.value = true
            topBarBackArrowVisibility.value = false
            bottomBarVisible.value = true
        }

        FAVORITE -> {
            topBarVisibility.value = true
            topBarBackArrowVisibility.value = false
            bottomBarVisible.value = true
        }

        GALLERY -> {
            topBarVisibility.value = true
            topBarBackArrowVisibility.value = false
            bottomBarVisible.value = true
        }

        PRODUCTION_DETAIL -> {
            topBarBackArrowVisibility.value = true
            bottomBarVisible.value = true
        }

        NEWS_DETAILS -> {
            topBarBackArrowVisibility.value = true
            bottomBarVisible.value = true
        }

        RECOMMENDATION_DETAILS -> {
            topBarBackArrowVisibility.value = true
            bottomBarVisible.value = true
        }

        else -> topBarVisibility.value = true
    }
    AdaptiveScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (topBarVisibility.value)
                ThesisTopBar(
                    backArrowVisibility = topBarBackArrowVisibility.value,
                    navController = navController,
                )
        },
        bottomBar = {
            if (bottomBarVisible.value)
                BottomNavigationBar(navController = navController)
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = HOME,
            modifier = Modifier.padding(it),
        ) {
            introductionGraph(navController)
            mainGraph(navController)
        }
    }
}