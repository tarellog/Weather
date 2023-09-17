package com.example.weather.network.common.converters

import com.example.weather.network.common.model.WeatherModel
import com.example.weather.usecases.common.TodayWeather
import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun WeatherModel.mapToHeaderDisplayModel(): TodayWeather = TodayWeather(
    date = simpleDateFormat.parse(list.first().dt_txt)!!,
    icon = list.first().weather.first().icon,
    temp = list.first().main.temp.toInt(),
    description = list.first().weather.first().description,
    wind = list.first().wind.speed.toInt(),
    humidity = list.first().main.humidity,
    cityName = city.name
)