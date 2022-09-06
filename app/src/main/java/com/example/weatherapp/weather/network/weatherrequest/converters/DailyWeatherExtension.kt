package com.example.weatherapp.weather.network.weatherrequest.converters

import com.example.weatherapp.weather.network.weatherrequest.model.ListWeatherModel
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun List<ListWeatherModel>.mapToDisplayModel(): List<DailyWeather> {
    return this
        .groupBy { it.toDailyWeather().date.day}
        .map {
            val date = simpleDateFormat.parse(it.value.first().dt_txt)
            val icon = it.value.first().weather.first().icon
            val hours = this.mapToHoursDisplayModel(it.value.first().toDailyWeather().date)
            val maxTemp = hours.map { it1 -> it1.tempHours }.maxOrNull()?.toInt() ?: 0
            val minTemp = hours.map { it1 -> it1.tempHours }.minOrNull()?.toInt() ?: 0
            DailyWeather(date, maxTemp, minTemp, icon, hours)
        }
}

fun ListWeatherModel.toDailyWeather() = DailyWeather(
    date = simpleDateFormat.parse(dt_txt)!!,
    minTemp = listOf(this.main.temp_min).minOrNull()?.toInt() ?: 0,
    maxTemp = listOf(this.main.temp_max).maxOrNull()?.toInt() ?: 0,
    icon = weather.first().icon,
    hoursList = listOf(toTimeWeather())
)
