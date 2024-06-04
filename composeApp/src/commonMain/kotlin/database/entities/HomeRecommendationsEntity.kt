package database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_recommendations")
data class HomeRecommendationsEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo("image_url") val imageUrl: String,
    @ColumnInfo("brand") val brand: String
)
