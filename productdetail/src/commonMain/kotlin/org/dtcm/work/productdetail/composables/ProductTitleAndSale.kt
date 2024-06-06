package org.dtcm.work.productdetail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.diploma.work.common.Res
import org.diploma.work.common.productsSalePercentage
import org.dtcm.work.design.fontSize16
import org.dtcm.work.design.fontSize20
import org.dtcm.work.design.normal100
import org.dtcm.work.design.normal150
import org.dtcm.work.design.small100
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ProductTitleAndSale(
    productTitle: String,
    salePercentage: Int,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = productTitle,
            modifier = Modifier
                .padding(horizontal = normal100, vertical = normal150)
                .weight(1f)
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize20,
        )
        Text(
            text = stringResource(Res.string.productsSalePercentage, salePercentage),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize16,
            modifier = Modifier
                .padding(normal100)
                .background(
                    color = MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(small100)
                )
                .align(Alignment.CenterVertically)
                .padding(horizontal = normal100, vertical = small100),
        )
    }
}
