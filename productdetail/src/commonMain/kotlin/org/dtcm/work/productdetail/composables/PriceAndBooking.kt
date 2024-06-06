package org.dtcm.work.productdetail.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.dtcm.work.common.data.data.ProductDetailsData
import org.dtcm.work.common.data.ui.Prices
import org.dtcm.work.design.MainButton
import org.dtcm.work.design.normal100
import org.dtcm.work.productdetail.Res
import org.dtcm.work.productdetail.product_is_booked
import org.dtcm.work.productdetail.want_book
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PriceAndBooking(
    isProductBooked: Boolean,
    productDetails: ProductDetailsData,
    bookingClicked: () -> Unit
) {
    println("isProductBooked: $isProductBooked")

    Row {
        Prices(
            originalPrice = productDetails.originalPrice.toString(),
            priceOnSale = productDetails.priceOnSale.toString(),
            modifier = Modifier
                .padding(start = normal100)
                .weight(1f)
                .align(Alignment.CenterVertically),
        )
        if (!isProductBooked) {
            MainButton(
                onClick = bookingClicked,
                content = { Text(text = stringResource(Res.string.want_book)) },
                modifier = Modifier
                    .padding(end = normal100)
                    .align(Alignment.CenterVertically),
            )
        } else {
            MainButton(
                onClick = bookingClicked,
                content = { Text(text = stringResource(Res.string.product_is_booked)) },
                modifier = Modifier
                    .padding(end = normal100)
                    .align(Alignment.CenterVertically),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }
    }
}