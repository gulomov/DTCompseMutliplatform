import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import home.HomeViewModel
import org.koin.compose.koinInject

@Composable
@Preview
fun App(viewModel: HomeViewModel = koinInject()) {
    MaterialTheme {

        val news by viewModel.news.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.getNews()
        }

        LazyColumn {
            items(news) {
                Text(it.title.toString())
            }
        }
    }
}
