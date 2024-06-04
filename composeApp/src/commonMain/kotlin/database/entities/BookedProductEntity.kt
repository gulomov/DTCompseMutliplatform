package database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "booked_products")
data class BookedProductEntity(
    @PrimaryKey val productId: Int,
    @ColumnInfo("booked_date") val bookedDate: Long
)
