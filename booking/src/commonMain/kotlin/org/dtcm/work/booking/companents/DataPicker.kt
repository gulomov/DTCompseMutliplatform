package org.dtcm.work.booking.companents

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveDatePicker
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTextButton
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.rememberCupertinoDatePickerState
import kotlinx.datetime.Clock
import org.dtcm.work.booking.Res
import org.dtcm.work.booking.data_picker_confirm_button
import org.dtcm.work.booking.data_picker_dismiss_button
import org.jetbrains.compose.resources.stringResource

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAdaptiveApi::class,
    ExperimentalCupertinoApi::class
)
@Composable
internal fun DataPicker(
    onConfirmClicked: (Long) -> Unit,
    onDismissClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    
    val currentTimeMillis = remember { Clock.System.now().toEpochMilliseconds() }
    val datePickerState = rememberCupertinoDatePickerState(
        yearRange = 2024..2024,
    )
    
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = { onDismissClicked() },
        confirmButton = {
            AdaptiveTextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { selectedDate ->
                        onConfirmClicked(selectedDate)
                    }
                },
                enabled = true
                /*datePickerState.selectedDateMillis?.let { selectedDate ->
                    selectedDate >= currentTimeMillis
                } == true*/ // TODO: Find out why not working.

            ) {
                Text(text = stringResource(Res.string.data_picker_confirm_button))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissClicked() }) {
                Text(text = stringResource(Res.string.data_picker_dismiss_button))
            }
        }) {
        AdaptiveDatePicker(state = datePickerState)
    }
}