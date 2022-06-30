package com.example.prostidnevi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.prostidnevi.data.HolidaysRepository
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import java.util.*

class CalendarViewModel(
    val holidaysRepository: HolidaysRepository = HolidaysRepository()
): ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    init {
    }

    fun prevMonth(){
        uiState = uiState.copy(selectedDate = uiState.selectedDate.minus(1, DateTimeUnit.MONTH))
    }

    fun nextMonth(){
        uiState = uiState.copy(selectedDate = uiState.selectedDate.plus(1, DateTimeUnit.MONTH))
    }

    fun getHolidays(): List<LocalDate>{
        return holidaysRepository.getHolidays()
    }
}