package org.dtcm.work.repository

import Converters
import database.entities.HomeRecommendationsEntity
import dev.gitlive.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.dtcm.work.common.data.NewsInfo
import org.dtcm.work.common.data.NewsItem
import org.dtcm.work.common.data.RecommendationItem
import org.dtcm.work.common.data.RecommendationsList
import org.dtcm.work.common.data.TopProductItem
import org.dtcm.work.common.data.TopProductsList
import org.dtcm.work.database.AppDatabase
import org.dtcm.work.database.entities.NewsInfoEntity
import org.dtcm.work.database.entities.TopProductsListEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.dtcm.work.repository.firebasehelper.fetchFromDatabase

private const val AMOUNT_OF_TOP_PRODUCTS_IN_HOME_SCREEN = 5

class HomeRepository(
    private val firebaseDatabase: FirebaseDatabase,
    private val roomDb: AppDatabase
) {

    suspend fun fetchAndSaveNewsInfoFromFirebase() {
        fetchFromDatabase<NewsInfo>("home/news", firebaseDatabase).collect { newsInfo ->
            newsInfo?.newsList?.let { newsList ->
                withContext(Dispatchers.IO) {
                    newsList.forEach {
                        roomDb.homeScreenDao().saveNewsInfo(
                            NewsInfoEntity(
                                id = it.id ?: 0,
                                image = it.image,
                                body = it.body,
                                title = it.title,
                            ),
                        )
                    }
                }
            }
        }
    }

    suspend fun fetchAndSaveHomeRecommendationsFromFirebase() {
        fetchFromDatabase<RecommendationsList>(
            "home/recommendations",
            firebaseDatabase,
        ).collect { data ->
            withContext(Dispatchers.IO) {
                data?.recommendationsList?.map {
                    roomDb.homeScreenDao().saveHomeRecommendations(
                        HomeRecommendationsEntity(
                            id = it.id ?: 0,
                            imageUrl = it.image.orEmpty(),
                            brand = it.brand.toString()
                        ),
                    )
                }
            }
        }
    }

    suspend fun fetchAndSaveTopProductsFromFirebase() {
        fetchFromDatabase<TopProductsList>(
            "home/topProducts",
            firebaseDatabase,
        ).collect { data ->
            withContext(Dispatchers.IO) {
                data?.topProductsList?.map {
                    roomDb.homeScreenDao().saveTopProductsList(
                        TopProductsListEntity(
                            address = it.address.orEmpty(),
                            id = it.id ?: 0,
                            imageUrl = Converters().fromImagesList(it.images.orEmpty()),
                            title = it.title.orEmpty(),
                            salePercentage = it.salePercentage ?: 0,
                            saleStartsDate = it.saleStartsDate.orEmpty(),
                            saleEndsDate = it.saleEndsDate.orEmpty(),
                            originalPrice = it.originalPrice ?: 0,
                            priceOnSale = it.priceOnSale ?: 0,
                            sizes = Converters().fromSizesList(it.sizes.orEmpty()),
                            brand = it.brand.toString()
                        ),
                    )
                }
            }
        }
    }

    fun getNewsInfo() = roomDb.homeScreenDao().getNewsInfoFlow().map { newsEntityList ->
        newsEntityList.map { entity ->
            NewsItem(
                id = entity.id,
                image = entity.image,
                body = entity.body,
                title = entity.title,
            )
        }
    }

    fun getRecommendations() =
        roomDb.homeScreenDao().getHomeRecommendationsFlow().map { recommendations ->
            recommendations.map {
                RecommendationItem(id = it.id, image = it.imageUrl, brand = it.brand)
            }
        }

    fun getTopProducts() = roomDb.homeScreenDao().getTopProductsFlow().map { topProducts ->
        topProducts.shuffled().take(AMOUNT_OF_TOP_PRODUCTS_IN_HOME_SCREEN).map {
            TopProductItem(
                id = it.id,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
            )
        }
    }

    fun getNewsDetail(id: Int) = roomDb.homeScreenDao().getNewsDetail(id).map {
        NewsItem(
            id = it.id,
            image = it.image,
            body = it.body,
            title = it.title,
        )
    }
}