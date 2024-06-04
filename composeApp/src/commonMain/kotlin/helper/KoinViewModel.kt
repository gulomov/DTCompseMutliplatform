package helper

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope

@Composable
inline fun <reified VM : ViewModel> koinViewModel(): VM {
    val scope = currentKoinScope()

    return viewModel {
        scope.get<VM>()
    }
}