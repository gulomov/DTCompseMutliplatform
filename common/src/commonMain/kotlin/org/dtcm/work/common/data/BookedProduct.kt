package org.dtcm.work.common.data

import kotlinx.serialization.Serializable
import org.dtcm.work.database.entities.BookedProductEntity

@Serializable
data class BookedProduct(
    val productId: Int? = null,
    val bookedDate: Long? = null
) {
    fun asEntity() = BookedProductEntity(productId ?: 0, bookedDate ?: 0)
}
