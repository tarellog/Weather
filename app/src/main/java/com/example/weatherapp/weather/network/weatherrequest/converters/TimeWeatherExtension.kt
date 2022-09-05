package com.example.weatherapp.weather.network.weatherrequest.converters

import com.example.weatherapp.weather.network.weatherrequest.model.ListWeatherModel
import com.example.weatherapp.weather.usecases.weatherloader.TimeWeather
import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

fun List<ListWeatherModel>.mapToHoursDisplayModel(hourDate: Date): List<TimeWeather> {
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
            it.toTimeWeather()
        }
}

fun ListWeatherModel.toTimeWeather(): TimeWeather = TimeWeather(
    timeHours = simpleDateFormat.parse(dt_txt)!!,
    tempHours = main.temp.toInt(),
    iconHours = weather.first().icon
)