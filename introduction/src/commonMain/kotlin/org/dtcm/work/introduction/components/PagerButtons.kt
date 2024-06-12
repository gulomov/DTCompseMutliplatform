package org.dtcm.work.introduction.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.launch
import org.dtcm.work.design.MainButton
import org.dtcm.work.design.small100
import org.dtcm.work.introduction.Res
import org.dtcm.work.introduction.introduction_button_previous
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerButtons(
    pagerState: PagerState,
    pageCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = modifier.fillMaxWidth()) {
        if (pagerState.currentPage > 0) {
            MainButton(
                modifier =
                Modifier
                    .padding(small100)
                    .wrapContentWidth()
                    .weight(1f),
                onClick = {
                    coroutineScope.launch {
                        val previousPage = pagerState.currentPage - 1
                        if (previousPage < pageCount) {
                            pagerState.animateScrollToPage(previousPage)
                        }
                    }
                },
                content = {
                    Text(
                        text = stringResource(resource = Res.string.introduction_button_previous),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
            )
        }
        MainButton(
            modifier = Modifier
                .padding(small100)
                .wrapContentWidth()
                .weight(1f),
            onClick = {
                coroutineScope.launch {
                    val nextPage = pagerState.currentPage + 1
                    if (nextPage < pageCount) {
                        pagerState.animateScrollToPage(nextPage)
                    } else {
                        onClick.invoke()
                    }
                }
            },
            content = {
                IntroductionNextButtonText(pagerState)
            },
        )
    }
}