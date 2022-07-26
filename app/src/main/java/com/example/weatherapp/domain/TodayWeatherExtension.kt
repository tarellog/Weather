package com.example.weatherapp.domain

import com.example.weatherapp.data.model.ListWeatherModel
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToHeaderDisplayModel() : BasedModel.TodayWeatherModel {
    return this.map {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(it.dt_txt)
        val icon = it.weather.first().icon
        val temp = it.main.temp.toInt()
        val description = it.weather.first().description
        BasedModel.TodayWeatherModel(date, icon, temp, description)
    }
        .first()
}