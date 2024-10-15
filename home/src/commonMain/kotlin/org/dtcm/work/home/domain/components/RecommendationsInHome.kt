package org.dtcm.work.home.domain.components

import BrandImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.dtcm.work.common.data.data.RecommendationItem
import org.dtcm.work.design.normal100
import org.dtcm.work.design.recommendationImageHeightSize
import org.dtcm.work.design.recommendationImageWidthSize
import org.dtcm.work.design.small100
import org.dtcm.work.design.small150
import org.dtcm.work.home.Res
import org.dtcm.work.home.homeScreenRecommendationsTitle
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RecommendationsInHome(
    recommendations: List<RecommendationItem>,
    onRecommendationClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { recommendations.size })
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(Res.string.homeScreenRecommendationsTitle),
            modifier = Modifier.padding(start = small150, top = small150),
            fontWeight = FontWeight.Bold,
        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(small150),
            pageSpacing = small100,
            pageSize = PageSize.Fixed(recommendationImageWidthSize),
        ) { page ->
            // FIXME: Change size
            Card(
                modifier = Modifier
                    .width(recommendationImageWidthSize),
                shape = RoundedCornerShape(normal100),
                onClick = {
                    onRecommendationClicked(recommendations[page].brand.toString())
                },
            ) {
                BrandImage(
                    imageUrl = recommendations[page].image.toString(),
                    modifier
                        .width(recommendationImageWidthSize)
                        .height(recommendationImageHeightSize),
                )
            }
        }
    }
}
