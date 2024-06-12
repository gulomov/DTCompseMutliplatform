package org.dtcm.work.introduction.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.dtcm.work.introduction.Res
import org.dtcm.work.introduction.introduction_button_finish
import org.dtcm.work.introduction.introduction_button_next
import org.dtcm.work.introduction.introduction_button_start
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroductionNextButtonText(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Text(
        text =
        when (pagerState.currentPage) {
            0 -> stringResource(Res.string.introduction_button_start)
            1 -> stringResource(Res.string.introduction_button_next)
            else -> stringResource(Res.string.introduction_button_finish)
        },
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth(),
    )
}