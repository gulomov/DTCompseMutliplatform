package org.dtcm.work.database.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductImages(
    val imageUrl: String = "",
)