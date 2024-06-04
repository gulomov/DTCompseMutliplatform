package org.dtcm.work.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.dtcm.work.database.entities.AllProductsListEntity
import org.dtcm.work.database.entities.BookedProductEntity
import org.dtcm.work.database.entities.BrandsListEntity
import org.dtcm.work.database.entities.FavoriteProductsEntity
import org.dtcm.work.database.entities.TopProductsListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Query("SELECT * FROM top_products_list WHERE id = :productId")
    fun getTopProductDetail(productId: String): Flow<TopProductsListEntity>

    @Query("SELECT * FROM all_products_list WHERE id = :productId")
    fun getProductDetail(productId: String): Flow<AllProductsListEntity>

    @Query("SELECT * FROM favorite_products")
    fun getFavouriteProducts(): Flow<List<FavoriteProductsEntity>>

    @Query("SELECT * FROM favorite_products WHERE id = :productId")
    fun getFavouriteProduct(productId: Int): Flow<FavoriteProductsEntity?>

    @Query("SELECT favorite_products.id FROM favorite_products")
    suspend fun getFavoriteProductIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavoriteProducts(favoriteProductsEntity: FavoriteProductsEntity)

    @Query("DELETE FROM favorite_products WHERE id = :id")
    suspend fun deleteFromFavoriteProducts(id: Int)

    @Query("SELECT * FROM all_products_list")
    fun getAllProductsFlow(): Flow<List<AllProductsListEntity>>

    @Query("SELECT * FROM all_products_list")
    suspend fun getAllProductsList(): List<AllProductsListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToAllProductsEntity(allProductsListEntity: AllProductsListEntity)

    @Query("SELECT * FROM brands_list")
    fun getAllBrandsFlow(): Flow<List<BrandsListEntity>>

    @Query("SELECT * FROM brands_list")
    suspend fun getAllBrandsList(): List<BrandsListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToBrandsEntity(brandsListEntity: BrandsListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToBookedProducts(bookedProduct: BookedProductEntity)

    @Query("SELECT * FROM booked_products WHERE productId = :productId")
    fun getBookedProductById(productId: Int): Flow<BookedProductEntity>

    @Query("SELECT * FROM booked_products")
    fun getBookedProducts(): Flow<List<BookedProductEntity>>
}