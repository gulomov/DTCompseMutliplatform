package org.dtcm.work.common.data.data

import kotlinx.serialization.Serializable

@Serializable
data class NewsInfo(
    val newsList: List<NewsItem>? = emptyList(),
    val title: String? = null,
)

@Serializable
    data class NewsItem(
    val id: Int? = null,
    val image: String? = null,
    val body: String? = null,
    val title: String? = null,
)
