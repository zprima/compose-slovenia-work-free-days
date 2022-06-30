package com.example.prostidnevi

import com.example.prostidnevi.util.getNumberOfWeeksInMonth
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.*

data class UiState(
    var selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    var locale: Locale = Locale("sl", "SI")
) {
    val weeks = selectedDate.getNumberOfWeeksInMonth()
}