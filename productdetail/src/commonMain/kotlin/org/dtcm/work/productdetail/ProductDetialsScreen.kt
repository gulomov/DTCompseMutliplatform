package org.dtcm.work.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.booking.BookingScreen
import org.dtcm.work.common.data.createSavedStateHandle
import org.dtcm.work.common.data.ui.TopProductsLazyRow
import org.dtcm.work.design.MainButton
import org.dtcm.work.design.normal100
import org.dtcm.work.design.normal150
import org.dtcm.work.productdetail.composables.PriceAndBooking
import org.dtcm.work.productdetail.composables.ProductDetailsImages
import org.dtcm.work.productdetail.composables.ProductSize
import org.dtcm.work.productdetail.composables.ProductTitleAndSale
import org.dtcm.work.productdetail.helper.getPlatformContext
import org.dtcm.work.productdetail.helper.openInMaps
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun ProductDetails(
    productId: String,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val context = getPlatformContext()
    val savedStateHandle = remember { createSavedStateHandle(mapOf("productId" to productId)) }
    val viewModel: ProductDetailsViewModel = koinInject { parametersOf(savedStateHandle) }
    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value.startBookingLogic) {
        BookingScreen(
            onCloseBooking = viewModel::checkIfProductBooked,
            productId = productId.toInt(),
            showBottomSheet = uiState.value.isProductBooked,
            showDatePicker = !uiState.value.isProductBooked,
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        ProductDetailsImages(
            productImages = uiState.value.productDetails.images,
            isProductSavedIntoFavorites = uiState.value.isProductInFavorites,
            saveProductIntoFavoritesClicked = { viewModel.handleSaveProductToFavorites() }
        )
        ProductTitleAndSale(
            uiState.value.productDetails.title,
            uiState.value.productDetails.salePercentage,
        )
        PriceAndBooking(
            isProductBooked = uiState.value.isProductBooked,
            productDetails = uiState.value.productDetails,
            bookingClicked = viewModel::startBookingLogic
        )
        Spacer(modifier = Modifier.height(normal150))
        ProductSize(uiState.value.productDetails.sizes)
        Text(
            text = stringResource(
                Res.string.sales_period,
                uiState.value.productDetails.saleStartsDate,
                uiState.value.productDetails.saleEndsDate,
            ),
            modifier = Modifier.padding(horizontal = normal100, vertical = normal150),
        )
        Text(
            text = stringResource(
                Res.string.sale_on_address,
                uiState.value.productDetails.address,
            ),
            modifier = Modifier.padding(horizontal = normal100),
        )
        MainButton(
            modifier = Modifier
                .padding(normal100)
                .fillMaxWidth(),
            onClick = { openInMaps(context, uiState.value.productDetails.address) },
            content = {
                Text(text = stringResource(Res.string.show_in_the_map))
            },
        )
        TopProductsLazyRow(
            productList = uiState.value.topProducts,
            navController = navController,
        )
    }
}
