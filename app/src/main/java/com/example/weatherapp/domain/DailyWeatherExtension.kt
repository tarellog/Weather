package com.example.weatherapp.domain

import com.example.weatherapp.data.model.ListWeatherModel
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToDisplayModel(): List<BasedModel.DailyWeatherModel> {
    var currentDay = Date()
    return this
        .map {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = sdf.parse(it.dt_txt)
            val hours = this.mapToHoursDisplayModel(date)
            val maxTemp = hours.map { it1 -> it1.tempHours }.maxOrNull()?.toInt() ?: 0
            val minTemp = hours.map { it1 -> it1.tempHours }.minOrNull()?.toInt() ?: 0
            val icon = it.weather.first().icon

            BasedModel.DailyWeatherModel(date, maxTemp, minTemp, icon, hours)
        }
        .filterIndexed { index, dailyWeatherModel ->
            if (index == 0) {
                currentDay = dailyWeatherModel.date
                true
            } else if (dailyWeatherModel.date.after(currentDay)) {
                currentDay = dailyWeatherModel.date
                true
            } else false
        }
}

