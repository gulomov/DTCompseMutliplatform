package org.dtcm.work.common.data.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100

private const val GRID_CELLS = 2

@Composable
fun MainLazyVerticalGrid(
    modifier: Modifier = Modifier,
    content: LazyGridScope.() -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(GRID_CELLS),
        contentPadding = PaddingValues(horizontal = normal100, vertical = small100),
        verticalArrangement = Arrangement.spacedBy(small100),
        horizontalArrangement = Arrangement.spacedBy(small100),
        content = content
    )
}