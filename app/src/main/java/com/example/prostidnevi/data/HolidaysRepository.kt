package com.example.prostidnevi.data

import kotlinx.datetime.LocalDate

class HolidaysRepository {
    fun getHolidays(): List<LocalDate> {
        return listOf(
            LocalDate(monthNumber = 1, dayOfMonth = 1, year = 2022),
            LocalDate(monthNumber = 1, dayOfMonth = 2, year = 2022),
            LocalDate(monthNumber = 2, dayOfMonth = 8, year = 2022),
            LocalDate(monthNumber = 4, dayOfMonth = 17, year = 2022),
            LocalDate(monthNumber = 4, dayOfMonth = 18, year = 2022),
            LocalDate(monthNumber = 4, dayOfMonth = 27, year = 2022),
            LocalDate(monthNumber = 5, dayOfMonth = 1, year = 2022),
            LocalDate(monthNumber = 5, dayOfMonth = 2, year = 2022),
            LocalDate(monthNumber = 6, dayOfMonth = 5, year = 2022),
            LocalDate(monthNumber = 6, dayOfMonth = 25, year = 2022),
            LocalDate(monthNumber = 8, dayOfMonth = 15, year = 2022),
            LocalDate(monthNumber = 10, dayOfMonth = 31, year = 2022),
            LocalDate(monthNumber = 11, dayOfMonth = 1, year = 2022),
            LocalDate(monthNumber = 12, dayOfMonth = 25, year = 2022),
            LocalDate(monthNumber = 12, dayOfMonth = 26, year = 2022),
        )
    }
}