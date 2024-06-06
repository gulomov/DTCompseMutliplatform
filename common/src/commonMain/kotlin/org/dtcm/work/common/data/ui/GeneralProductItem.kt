import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.dtcm.work.common.data.ui.GenericProductImages
import org.dtcm.work.common.data.ui.Prices
import org.dtcm.work.design.normal100
import org.dtcm.work.design.small100
import org.dtcm.work.design.small150

@Composable
fun <T> GenericProductItem(
    item: T,
    onClick: (T) -> Unit,
    onSaveOrDeleteClick: (Boolean) -> Unit,
    productImagesList: List<String>,
    productPercentage: String,
    title: String,
    originalPrice: String,
    priceOnSale: String,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onClick(item) },
        modifier = modifier.padding(horizontal = normal100, vertical = small150),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceTint),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            GenericProductImages(
                imageUrls = productImagesList,
                isFavorite = isFavorite,
                productPercentage = productPercentage,
                onSaveClick = {
                    onSaveOrDeleteClick(it)
                }
            )
            Spacer(modifier = Modifier.height(small100))
            Text(
                text = title,
                modifier = Modifier
                    .padding(small100)
                    .align(Alignment.Start),
                maxLines = 1
            )
            Prices(
                originalPrice = originalPrice,
                priceOnSale = priceOnSale,
                modifier = Modifier.padding(start = small100),
            )
            Spacer(modifier = Modifier.height(small100))
        }
    }
}
