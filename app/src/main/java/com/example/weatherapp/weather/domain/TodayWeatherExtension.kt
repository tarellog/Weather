package com.example.weatherapp.weather.domain

import com.example.weatherapp.weather.network.model.ListWeatherModel
import com.example.weatherapp.weather.usecases.weatherloader.TodayWeather
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