package com.example.prostidnevi.util

import kotlinx.datetime.LocalDate
import kotlin.math.ceil

fun LocalDate.isLeapYear(): Boolean {
    return when {
        year % 4 == 0 -> {
            when {
                year % 100 == 0 -> year % 400 == 0
                else -> true
            }
        }
        else -> false
    }
}

fun LocalDate.getNumberOfWeeksInMonth(): Int {
    val lastDay = month.length(isLeapYear())

    return ceil(lastDay / 7.0).toInt()
}