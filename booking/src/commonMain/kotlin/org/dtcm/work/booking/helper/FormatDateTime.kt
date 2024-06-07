package org.dtcm.work.booking.helper

import kotlinx.datetime.LocalDateTime


fun formatDateTime(dateTime: LocalDateTime?, pattern: String): String {
    val year = dateTime?.year.toString()
    val month = dateTime?.monthNumber.toString().padStart(2, '0')
    val day = dateTime?.dayOfMonth.toString().padStart(2, '0')

    return pattern
        .replace("yyyy", year)
        .replace("MM", month)
        .replace("dd", day)
}