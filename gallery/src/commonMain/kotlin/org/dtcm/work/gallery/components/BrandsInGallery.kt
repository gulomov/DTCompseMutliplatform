package org.dtcm.work.gallery.components

import BrandImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.dtcm.work.common.data.data.BrandsItem
import org.dtcm.work.design.BrandsInGalleryImageHeightSize
import org.dtcm.work.design.BrandsInGalleryImageWidthSize
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BrandsInGallery(
    brandsList: List<BrandsItem>,
    onBrandClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { brandsList.size })

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = normal100, vertical = small100),
        pageSpacing = small100,
        pageSize = PageSize.Fixed(BrandsInGalleryImageWidthSize),
    ) { page ->
        Card(
            modifier = Modifier
                .width(BrandsInGalleryImageWidthSize),
            shape = RoundedCornerShape(small100),
            onClick = { onBrandClicked(brandsList[page].brand.toString()) }
        ) {
            BrandImage(
                imageUrl = brandsList[page].imageUrl.toString(),
                modifier = Modifier
                    .width(BrandsInGalleryImageWidthSize)
                    .height(BrandsInGalleryImageHeightSize),
            )
        }
    }
}