package com.example.prostidnevi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prostidnevi.CalendarViewModel
import com.example.prostidnevi.R
import kotlinx.datetime.*
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarApp(
    viewModel: CalendarViewModel = viewModel()
){
    val uiState = viewModel.uiState

    Column() {
        Spacer(Modifier.height(32.dp))
        MonthSection(
            selectedDate = uiState.selectedDate,
            locale = uiState.locale,
            onPrevMonth = { viewModel.prevMonth() },
            onNextMonth = { viewModel.nextMonth() }
        )
        Spacer(Modifier.height(32.dp))
        CalendarSection(
            weeks = uiState.weeks,
            selectedDate = uiState.selectedDate,
            holidays = viewModel.getHolidays()
        )
    }
}

@Composable
fun MonthSection(selectedDate: LocalDate, locale: Locale, onPrevMonth: () -> Unit, onNextMonth: () -> Unit){

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){

        IconButton(onClick = { onPrevMonth() }) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
        }

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = selectedDate.month.getDisplayName(TextStyle.FULL, locale)
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(locale) else it.toString()
                },
                fontSize = 22.sp
            )

            Text(selectedDate.year.toString())
        }

        IconButton(onClick = { onNextMonth() }) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
fun CalendarSection(weeks: Int, selectedDate: LocalDate, holidays: List<LocalDate>){
    val daysPerWeek = mutableMapOf<Int, List<LocalDate?>>()
    var firstWeekDay = selectedDate.minus(selectedDate.dayOfMonth - 1, DateTimeUnit.DAY)
    for(i in 0 until weeks) {
        val listOfDays = mutableListOf<LocalDate?>()

        if(firstWeekDay.dayOfWeek.value != 0){
            repeat(firstWeekDay.dayOfWeek.value - 1){
                listOfDays.add(null)
            }
        }

        for (j in (firstWeekDay.dayOfWeek.value - 1) until DayOfWeek.values().count()) {
            if(firstWeekDay.month.value != selectedDate.month.value){
                listOfDays.add(null)

            } else {
                listOfDays.add(firstWeekDay)
                firstWeekDay = firstWeekDay.plus(1, DateTimeUnit.DAY)
            }
        }

        daysPerWeek[i] = listOfDays
    }

    Column(Modifier) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            stringArrayResource(id = R.array.days).forEach {
                Text(
                    text = it.substring(IntRange(0,2)),
                    modifier = Modifier.size(50.dp, 50.dp).padding(8.dp)
                )
            }
        }

        repeat(weeks) {
            CalendarGrid(
                days = daysPerWeek[it]!!,
                holidays = holidays
            )
        }
    }
}

@Composable
fun CalendarGrid(days: List<LocalDate?>, holidays: List<LocalDate>){
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        repeat(days.size){
            val day = days[it]
            if(day == null){
                Text(
                    text = "",
                    modifier = Modifier.size(50.dp, 50.dp).padding(8.dp),
                )
            } else {

                val color =
                    if (holidays.any { holiday -> holiday.month == day.month && holiday.dayOfMonth == day.dayOfMonth }) Color.Red else Color.Black

                val bcgColor =
                    if(day.dayOfWeek.value > 5) Color.LightGray else Color.Transparent

                Text(
                    text = day.dayOfMonth.toString(),
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .background(bcgColor)
                        .padding(8.dp),
                    color = color,
                    textAlign = TextAlign.Center,
                    fontWeight = if(color == Color.Red) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}
