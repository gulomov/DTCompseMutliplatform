package org.dtcm.work.repository

import database.entities.HomeRecommendationsEntity
import dev.gitlive.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.dtcm.work.common.data.NewsInfo
import org.dtcm.work.common.data.NewsItem
import org.dtcm.work.common.data.RecommendationsList
import org.dtcm.work.database.AppDatabase
import org.dtcm.work.database.entities.NewsInfoEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.dtcm.work.repository.firebasehelper.fetchFromDatabase

class HomeRepository : KoinComponent {
    private val firebaseDatabase: FirebaseDatabase by inject()
    private val roomDb: AppDatabase by inject()

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

    fun getNewsInfo() = roomDb.homeScreenDao().getNewsInfoFlow().map { newsEntityList ->
        newsEntityList.map { entity ->
            NewsItem(
                id = entity.id,
                image = entity.image,
                body = entity.body,
                title = entity.title
            )
        }
    }
}