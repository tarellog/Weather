package com.example.weatherapp.weather.network.weatherrequest.converters

import com.example.weatherapp.weather.network.weatherrequest.model.ListWeatherModel
import com.example.weatherapp.weather.usecases.common.TodayWeather
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToHeaderDisplayModel() : TodayWeather {
    return this
        .map {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(it.dt_txt)
        val icon = it.weather.first().icon
        val temp = it.main.temp.toInt()
        val description = it.weather.first().description
        TodayWeather(date, icon, temp, description)
    }
        .first()
}