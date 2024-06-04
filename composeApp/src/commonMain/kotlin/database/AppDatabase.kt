package database

import Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.diploma.work.database.dao.HomeScreenDao
import database.dao.ProductsDao
import database.entities.BookedProductEntity
import database.entities.BrandsListEntity
import database.entities.FavoriteProductsEntity
import database.entities.HomeRecommendationsEntity
import database.entities.NewsInfoEntity
import database.entities.ProductListEntity
import database.entities.TopProductsListEntity
import database.entities.AllProductsListEntity

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
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun productDao(): ProductsDao
    abstract fun homeScreenDao(): HomeScreenDao
    override fun clearAllTables() {
        super.clearAllTables()
    }
}


// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}