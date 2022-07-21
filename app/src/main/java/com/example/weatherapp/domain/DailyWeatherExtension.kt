package com.example.weatherapp.domain

import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.model.custommodel.DailyWeatherModel
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToDisplayModel(): List<DailyWeatherModel> {
    return this.map {
        val date = it.dt_txt
        val minTemp = it.main.temp_min.toInt()
        val maxTemp = it.main.temp_max.toInt()
        val icon = it.weather.first().icon
        DailyWeatherModel(date, minTemp, maxTemp, icon)
    }
}