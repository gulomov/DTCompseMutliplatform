package org.dtcm.work.splashscreen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.dtcm.work.common.data.navigation.ScreenRoute.HOME
import org.dtcm.work.common.data.navigation.ScreenRoute.INTRODUCTION
import org.dtcm.work.common.data.navigation.ScreenRoute.INTRO_SPLASH
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = koinInject(),
) {
    val scale = remember { androidx.compose.animation.core.Animatable(0f) }
    val wasIntroductionShown by viewModel.wasIntroductionShown.collectAsState()

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec =
            tween(durationMillis = 800),
        )
        delay(1000L)
        if (!wasIntroductionShown) {
            INTRODUCTION
        } else {
            HOME
        }.let {
            navController.navigate(it) {
                popUpTo(INTRO_SPLASH) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold { innerPaddingModifier ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(innerPaddingModifier),
        ) {
            Image(
                painter = painterResource(Res.drawable.app_logo),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value),
            )
        }
    }
}
