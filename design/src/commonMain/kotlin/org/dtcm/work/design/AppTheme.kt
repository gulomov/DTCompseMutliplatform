package org.dtcm.work.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTheme
import io.github.alexzhirkevich.cupertino.adaptive.CupertinoThemeSpec
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.MaterialThemeSpec

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    AdaptiveTheme(
        material = MaterialThemeSpec(
            colorScheme = if (useDarkTheme) {
                androidx.compose.material3.darkColorScheme()
            } else {
                androidx.compose.material3.lightColorScheme()
            },
        ),
        cupertino = CupertinoThemeSpec(
            colorScheme = if (useDarkTheme) {
                io.github.alexzhirkevich.cupertino.theme.darkColorScheme()
            } else {
                io.github.alexzhirkevich.cupertino.theme.lightColorScheme()
            },
        ),
        content = content
    )
}