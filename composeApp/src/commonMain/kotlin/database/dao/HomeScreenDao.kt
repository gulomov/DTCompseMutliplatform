package org.diploma.work.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import database.entities.HomeRecommendationsEntity
import database.entities.NewsInfoEntity
import database.entities.TopProductsListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeScreenDao {
    @Query("SELECT * FROM news_info")
    fun getNewsInfoFlow(): Flow<List<NewsInfoEntity>>

    @Query("SELECT * FROM news_info")
    suspend fun getNewsInfoList(): List<NewsInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewsInfo(newsInfoEntity: NewsInfoEntity)

    @Query("SELECT * FROM home_recommendations")
    fun getHomeRecommendationsFlow(): Flow<List<HomeRecommendationsEntity>>

    @Query("SELECT * FROM home_recommendations")
    suspend fun getHomeRecommendationsList(): List<HomeRecommendationsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHomeRecommendations(homeRecommendationsEntity: HomeRecommendationsEntity)

    @Query("SELECT * FROM top_products_list")
    suspend fun getTopProductsList(): List<TopProductsListEntity>

    @Query("SELECT * FROM top_products_list")
    fun getTopProductsFlow(): Flow<List<TopProductsListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTopProductsList(topProductsListEntity: TopProductsListEntity)

    @Query("SELECT * FROM news_info WHERE id = :id ")
    fun getNewsDetail(id: Int): Flow<NewsInfoEntity>
}
