package com.example.weatherapp.weather.network.weatherrequest.converters

import com.example.weatherapp.weather.network.weatherrequest.model.ListWeatherModel
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun List<ListWeatherModel>.mapToDisplayModel(): List<DailyWeather> {
    var currentDay = Date()
    return this
        .map {
            val hours = this.mapToHoursDisplayModel(it.toDailyWeather().date)
            val maxTemp = hours.map { it1 -> it1.tempHours }.maxOrNull()?.toInt() ?: 0
            val minTemp = hours.map { it1 -> it1.tempHours }.minOrNull()?.toInt() ?: 0
            DailyWeather(it.toDailyWeather().date, maxTemp, minTemp, it.toDailyWeather().icon, hours)
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

fun ListWeatherModel.toDailyWeather() = DailyWeather(
    date = simpleDateFormat.parse(dt_txt)!!,
    minTemp = listOf(this).map { it.toTimeWeather().tempHours }.minOrNull()?.toInt() ?: 0,
    maxTemp = listOf(this).map { it.toTimeWeather().tempHours }.maxOrNull()?.toInt() ?: 0,
    icon = weather.first().icon,
    hoursList = listOf(this).mapToHoursDisplayModel(simpleDateFormat.parse(dt_txt)!!)
)
