package database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_list")
data class ProductListEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "product_name") val productName: String?,
    @ColumnInfo(name = "brand_name") val brandName: String?,
)
