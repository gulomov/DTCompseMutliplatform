package repository

import dev.gitlive.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CompletableDeferred
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repository.data.NewsInfo

class HomeRepository : KoinComponent {
    private val firebaseDatabase: FirebaseDatabase by inject()
    suspend fun fetchAndSaveNewsInfoFromFirebase(): NewsInfo? {
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
    }

}