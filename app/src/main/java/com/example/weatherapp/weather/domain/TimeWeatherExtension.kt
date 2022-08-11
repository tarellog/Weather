package com.example.weatherapp.weather.domain

import com.example.weatherapp.weather.network.model.ListWeatherModel
import com.example.weatherapp.weather.usecases.loaderweather.TimeWeatherModel
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToHoursDisplayModel(hourDate: Date): List<TimeWeatherModel> {
    return this
        .filter {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val date = sdf.parse(it.dt_txt)

            val dd = SimpleDateFormat("dd", Locale.getDefault())
            val day = dd.format(date)

            val clickedDay = dd.format(hourDate)
            day == clickedDay
        }
        .map {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val timeHours = sdf.parse(it.dt_txt)
            val tempHours = it.main.temp.toInt()
            val iconHours = it.weather.first().icon
            TimeWeatherModel(timeHours, tempHours, iconHours)
        }
}