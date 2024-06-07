package org.dtcm.work.booking.companents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.dtcm.work.booking.Res
import org.dtcm.work.booking.booked_date_title
import org.dtcm.work.booking.button_book_text
import org.dtcm.work.booking.button_nope_text
import org.dtcm.work.booking.helper.formatDateTime
import org.dtcm.work.design.MainButton
import org.dtcm.work.design.normal100
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetConfirmation(
    bookedProductDate: Long?,
    onDismissBottomSheet: () -> Unit,
    onRebookClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(bookedProductDate) {
        val pattern = "yyyy-MM-dd"
        val dateTime = bookedProductDate?.let {
            Instant.fromEpochMilliseconds(it)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        }
        formatDateTime(dateTime, pattern)
    }

    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismissBottomSheet,
        sheetState = modalBottomSheetState,
        modifier = modifier
    ) {
        Column(modifier = Modifier) {
            Text(
                text = stringResource(Res.string.booked_date_title, formattedDate),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(normal100)
            )
            Row {
                MainButton(
                    onClick = { onRebookClicked() },
                    content = { Text(text = stringResource(Res.string.button_book_text)) },
                    modifier = modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(normal100),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                )
                MainButton(
                    onClick = { onDismissBottomSheet() },
                    content = { Text(text = stringResource(Res.string.button_nope_text)) },
                    modifier = modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(normal100),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                )
            }
            Spacer(modifier = Modifier.height(normal100))
        }
    }
}