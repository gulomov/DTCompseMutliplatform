package org.dtcm.work.home.domain

import org.dtcm.work.common.data.data.NewsItem
import org.dtcm.work.common.data.data.RecommendationItem
import org.dtcm.work.common.data.data.TopProductItem

data class HomeUiState(
    val isLoading: Boolean = false,
    val navigationRoute: String? = null,
    val newsInfo: List<NewsItem> = emptyList(),
    val recommendationsList: List<RecommendationItem> = emptyList(),
    val topProductsList: List<TopProductItem> = emptyList()
)
