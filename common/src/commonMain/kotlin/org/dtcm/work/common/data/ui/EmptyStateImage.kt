package org.dtcm.work.common.data.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.dtcm.work.common.Res
import org.dtcm.work.common.empty_state
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyStateImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(Res.drawable.empty_state),
        contentDescription = null,
        modifier = modifier
    )
}