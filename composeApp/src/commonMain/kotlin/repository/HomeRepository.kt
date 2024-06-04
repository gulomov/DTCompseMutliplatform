package repository

import dev.gitlive.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.dtcm.work.database.AppDatabase
import org.dtcm.work.database.entities.NewsInfoEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.data.NewsInfo
import repository.data.NewsItem

class HomeRepository : KoinComponent {
    private val firebaseDatabase: FirebaseDatabase by inject()
    private val roomDb: AppDatabase by inject()
    /*suspend fun fetchAndSaveNewsInfoFromFirebase(): NewsInfo? {
        val deferred = CompletableDeferred<NewsInfo?>()

        try {
            fetchFromDatabase<NewsInfo>("home/news", firebaseDatabase).collect { newsInfo ->
                if (newsInfo != null) {
                    deferred.complete(newsInfo)
                    println("newsInfo in DTComposableMultiplatform: $newsInfo")
                } else {
                    deferred.complete(null)
                }
            }
        } catch (e: Exception) {
            deferred.completeExceptionally(e)
        }
        return deferred.await()
    }*/

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