package com.example.weatherapp.weather.network.weatherrequest.converters

import com.example.weatherapp.weather.network.weatherrequest.model.ListWeatherModel
import com.example.weatherapp.weather.usecases.common.DailyWeather
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToDisplayModel(): List<DailyWeather> {
    var currentDay = Date()
    return this
        .map {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = sdf.parse(it.dt_txt)
            val hours = this.mapToHoursDisplayModel(date)
            val maxTemp = hours.map { it1 -> it1.tempHours }.maxOrNull()?.toInt() ?: 0
            val minTemp = hours.map { it1 -> it1.tempHours }.minOrNull()?.toInt() ?: 0
            val icon = it.weather.first().icon

            DailyWeather(date, maxTemp, minTemp, icon, hours)
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

