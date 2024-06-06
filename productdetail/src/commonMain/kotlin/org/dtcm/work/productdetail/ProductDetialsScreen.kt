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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.dtcm.work.common.data.createSavedStateHandle
import org.dtcm.work.common.data.ui.TopProductsLazyRow
import org.dtcm.work.design.MainButton
import org.dtcm.work.design.normal100
import org.dtcm.work.design.normal150
import org.dtcm.work.productdetail.composables.PriceAndBooking
import org.dtcm.work.productdetail.composables.ProductDetailsImages
import org.dtcm.work.productdetail.composables.ProductSize
import org.dtcm.work.productdetail.composables.ProductTitleAndSale
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun ProductDetails(
    productId: String,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val savedStateHandle = remember { createSavedStateHandle(mapOf("productId" to productId)) }
    val viewModel: ProductDetailsViewModel = koinInject { parametersOf(savedStateHandle) }
    //val context = LocalContext.current
    val productDetails by viewModel.productDetails.collectAsState()
    val topProducts by viewModel.topProductsList.collectAsState()
    val isProductSavedIntoFavorites by viewModel.isProductInFavorites.collectAsState()
    val startBookingLogic by viewModel.startBookingLogic.collectAsState()
    val isProductBooked by viewModel.isProductBooked.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTopProductsList()
    }

    /*if (startBookingLogic) {
        if (isProductBooked)
            BookingScreen(
                onCloseBooking = { viewModel.checkIfProductBooked() },
                productId = productDetails.id ?: 0,
                showBottomSheet = true,
                showDatePicker = false,
            ) else {
            BookingScreen(
                onCloseBooking = { viewModel.checkIfProductBooked() },
                productId = productDetails.id ?: 0,
                showBottomSheet = false,
                showDatePicker = true,
            )
        }
    }*/

    productDetails.images?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            ProductDetailsImages(
                productImages = data,
                isProductSavedIntoFavorites = isProductSavedIntoFavorites,
                saveProductIntoFavoritesClicked = {
                    if (it) {
                        viewModel.saveProductToFavorites(productDetails)
                    } else {
                        productDetails.id?.let { productId ->
                            viewModel.deleteFromFavoriteProducts(
                                productId
                            )
                        }
                    }
                })
            ProductTitleAndSale(
                productDetails.title.orEmpty(),
                productDetails.salePercentage ?: 0,
            )
            PriceAndBooking(
                isProductBooked = isProductBooked,
                productDetails = productDetails,
                bookingClicked = {
                    viewModel.startBookingLogic(shouldShowBookingLogic = true)
                })
            Spacer(modifier = Modifier.height(normal150))
            ProductSize(productDetails.sizes.orEmpty())
            Text(
                text = stringResource(
                    Res.string.sales_period,
                    productDetails.saleStartsDate.orEmpty(),
                    productDetails.saleEndsDate.orEmpty(),
                ),
                modifier =
                Modifier
                    .padding(horizontal = normal100, vertical = normal150),
            )
            Text(
                text = stringResource(
                    Res.string.sale_on_address,
                    productDetails.address.orEmpty(),
                ),
                modifier = Modifier.padding(horizontal = normal100),
            )
            MainButton(
                modifier = Modifier
                    .padding(normal100)
                    .fillMaxWidth(),
                onClick = { /*openGoogleMaps(context, productDetails.address.orEmpty()) */ },
                content = {
                    Text(text = stringResource(Res.string.show_in_the_map))
                },
            )
            TopProductsLazyRow(
                productList = topProducts,
                navController = navController,
            )
        }
    }
}

/*
private fun openGoogleMaps(content: Context, address: String) = address.let {
    val intentUri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
    val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    content.startActivity(mapIntent)
}
*/
