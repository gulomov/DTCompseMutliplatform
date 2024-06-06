package org.dtcm.work.productdetail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.dtcm.work.database.data.ProductSizes
import org.dtcm.work.design.fontSize16
import org.dtcm.work.design.small100
import org.dtcm.work.design.small150

@Composable
fun ProductSize(
    sizes: List<ProductSizes>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .padding(start = small150)
            .background(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(small100),
            )

    ) {
        items(sizes) {
            ProductSize(size = it.size)
        }
    }
}

@Composable
fun ProductSize(
    size: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = size,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize16,
        modifier = modifier.padding(small100),
    )
}
