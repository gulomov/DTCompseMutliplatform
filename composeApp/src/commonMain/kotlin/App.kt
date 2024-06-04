import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import repository.data.NewsInfo
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import dtcompsemutliplatform.composeapp.generated.resources.Res
import dtcompsemutliplatform.composeapp.generated.resources.compose_multiplatform
import home.HomeViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@Preview
fun App(viewModel: HomeViewModel = koinInject()) {
    MaterialTheme {
        var newsInfo by remember { mutableStateOf<NewsInfo?>(null) }
        val scope = rememberCoroutineScope()

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                scope.launch {
                    println("DTCompose: $newsInfo")
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
                    println("DTCompose: $newsInfo")
                }
            } else {
                Text("Loading...")
            }
        }
    }
}
