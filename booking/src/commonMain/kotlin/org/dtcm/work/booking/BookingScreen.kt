package org.dtcm.work.booking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.dtcm.work.booking.companents.BottomSheetConfirmation
import org.dtcm.work.booking.companents.DataPicker
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.compose.koinInject

@Composable
fun BookingScreen(
    onCloseBooking: () -> Unit,
    productId: Int,
    showDatePicker: Boolean,
    showBottomSheet: Boolean,
    modifier: Modifier = Modifier,
    viewModel: BookingScreenViewModel = koinInject()
) {
    viewModel.getBookedProductDetail(productId)

    val showDatePickerStateFlow = remember { MutableStateFlow(showDatePicker) }
    val showBottomSheetStateFlow = remember { MutableStateFlow(showBottomSheet) }

    val showDatePickerState by showDatePickerStateFlow.collectAsState()
    val showBottomSheetState by showBottomSheetStateFlow.collectAsState()
    val bookedProductDate by viewModel.bookedProductDate.collectAsState()

    if (showBottomSheetState) {
        BottomSheetConfirmation(
            bookedProductDate = bookedProductDate,
            onDismissBottomSheet = {
                onCloseBooking()
            },
            onRebookClicked = {
                showDatePickerStateFlow.value = true
                showBottomSheetStateFlow.value = false
            }
        )
    }

    if (showDatePickerState) {
        DataPicker(
            modifier = modifier,
            onConfirmClicked = {
                onCloseBooking()
                viewModel.saveBookedProduct(productId, it)
            },
            onDismissClicked = { onCloseBooking() }
        )
    }
}