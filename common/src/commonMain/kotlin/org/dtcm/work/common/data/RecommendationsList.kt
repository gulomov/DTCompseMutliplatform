package org.dtcm.work.common.data

import kotlinx.serialization.Serializable

@Serializable
data class RecommendationsList(
    val recommendationsList: List<RecommendationItem>? = emptyList(),
)
@Serializable
data class RecommendationItem(
    val id: Int? = null,
    val image: String? = null,
    val brand: String? = null
)
