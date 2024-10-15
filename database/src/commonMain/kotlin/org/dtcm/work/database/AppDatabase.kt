package org.dtcm.work.database

import Converters
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import org.dtcm.work.database.dao.HomeScreenDao
import org.dtcm.work.database.dao.ProductsDao
import org.dtcm.work.database.entities.BookedProductEntity
import org.dtcm.work.database.entities.BrandsListEntity
import org.dtcm.work.database.entities.FavoriteProductsEntity
import database.entities.HomeRecommendationsEntity
import org.dtcm.work.database.entities.NewsInfoEntity
import org.dtcm.work.database.entities.ProductListEntity
import org.dtcm.work.database.entities.TopProductsListEntity
import org.dtcm.work.database.entities.AllProductsListEntity

@Database(
    entities = [
        ProductListEntity::class,
        NewsInfoEntity::class,
        HomeRecommendationsEntity::class,
        TopProductsListEntity::class,
        FavoriteProductsEntity::class,
        AllProductsListEntity::class,
        BrandsListEntity::class,
        BookedProductEntity::class,
    ],
    version = 1,
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductsDao
    abstract fun homeScreenDao(): HomeScreenDao

}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

