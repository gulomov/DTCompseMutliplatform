package org.dtcm.work.introduction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.common.data.navigation.ScreenRoute.HOME
import org.dtcm.work.common.data.navigation.ScreenRoute.INTRODUCTION
import org.dtcm.work.design.large100
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100
import org.dtcm.work.introduction.components.IntroductionContent
import org.dtcm.work.introduction.components.PagerButtons
import org.dtcm.work.introduction.components.PagerIndicators
import org.koin.compose.koinInject

private const val PAGER_COUNT = 3

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroductionScreen(
    navController: NavController,
    viewModel: IntroductionViewModel = koinInject(),
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = {
        PAGER_COUNT
    })

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(normal100),
    ) {
        Column {
            Spacer(modifier = Modifier.height(large100))
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                state = pagerState,
                contentPadding = PaddingValues(small100),
                pageSpacing = small100,
            ) { page ->
                IntroductionContent(page = page)
            }
            Spacer(modifier = Modifier.height(normal100))
            PagerIndicators(pagerState = pagerState, pageCount = PAGER_COUNT)
            PagerButtons(
                pagerState = pagerState,
                pageCount = PAGER_COUNT,
                onClick = {
                    viewModel.setIntroductionShown()
                    navController.navigate(HOME) {
                        popUpTo(INTRODUCTION) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}