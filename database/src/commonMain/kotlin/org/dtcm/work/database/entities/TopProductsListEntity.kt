package org.dtcm.work.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "top_products_list")
data class TopProductsListEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("address") val address: String,
    @ColumnInfo(name = "images") val imageUrl: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "sale_percentage") val salePercentage: Int,
    @ColumnInfo(name = "sale_starts_date") val saleStartsDate: String,
    @ColumnInfo(name = "sale_ends_date") val saleEndsDate: String,
    @ColumnInfo(name = "original_price") val originalPrice: Int,
    @ColumnInfo(name = "price_on_sale") val priceOnSale: Int,
    @ColumnInfo(name = "sizes") val sizes: String,
    @ColumnInfo(name = "brand") val brand: String
)