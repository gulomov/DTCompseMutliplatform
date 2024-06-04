import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.NewsInfo
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import dtcompsemutliplatform.composeapp.generated.resources.Res
import dtcompsemutliplatform.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    MaterialTheme {
        var newsInfo by remember { mutableStateOf<NewsInfo?>(null) }
        val scope = rememberCoroutineScope()

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                scope.launch {
                    newsInfo = fetchAndSaveNewsInfoFromFirebase()
                    println("newsInfo: $newsInfo")
                }
            }) {
                Text("Click me!")
            }

            if (newsInfo?.title != null) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text(newsInfo?.title ?: "No Title")
                    println("newsInfo: $newsInfo")
                }
            } else {
                Text("Loading...")
            }
        }
    }
}


suspend fun fetchAndSaveNewsInfoFromFirebase(): NewsInfo? {
    val firebaseDatabase =
        Firebase.database("https://composemultiplatformtext-default-rtdb.europe-west1.firebasedatabase.app/")
    val deferred = CompletableDeferred<NewsInfo?>()

    try {
        fetchFromDatabase<NewsInfo>("home/news", firebaseDatabase).collect { newsInfo ->
            if (newsInfo != null) {
                deferred.complete(newsInfo)
                println("newsInfo in function: $newsInfo")
            } else {
                deferred.complete(null)
            }
        }
    } catch (e: Exception) {
        deferred.completeExceptionally(e)
    }

    return deferred.await()
}
