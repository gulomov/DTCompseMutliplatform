package database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brands_list")
data class BrandsListEntity(
    @PrimaryKey val brandId: Int = 0,
    @ColumnInfo("image_url") val imageUrl: String,
    @ColumnInfo("brand") val brand: String
)
