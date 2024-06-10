import androidx.compose.runtime.*
import org.dtcm.work.design.AppTheme
import org.dtcm.work.navigationcomposables.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavHost()
    }
}
