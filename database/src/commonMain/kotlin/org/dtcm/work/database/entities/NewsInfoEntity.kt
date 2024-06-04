package org.dtcm.work.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_info")
data class NewsInfoEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "title") val title: String?,
)
