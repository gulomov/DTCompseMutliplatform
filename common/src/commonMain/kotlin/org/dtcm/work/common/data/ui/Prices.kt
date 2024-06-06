package org.dtcm.work.common.data.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import org.diploma.work.common.Res
import org.diploma.work.common.product_price
import org.dtcm.work.design.fontSize16
import org.dtcm.work.design.fontSize18
import org.jetbrains.compose.resources.stringResource

@Composable
fun Prices(
    originalPrice: String,
    priceOnSale: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(Res.string.product_price, originalPrice),
            style = TextStyle(
                color = Color.Gray,
                fontSize = fontSize16,
                textDecoration = TextDecoration.LineThrough,
            ),
        )
        Text(
            text = stringResource(Res.string.product_price, priceOnSale),
            fontSize = fontSize18,
        )
    }
}
