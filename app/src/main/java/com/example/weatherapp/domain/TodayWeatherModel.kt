package com.example.weatherapp.domain

import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.model.custommodel.TodayWeatherModel
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToHeaderDisplayModel() : List<TodayWeatherModel> {
    var currentDay = Date()
    return this.map {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(it.dt_txt)
        val icon = it.weather.first().icon
        val temp = (it.main.temp -273f).toInt()
        val description = it.weather.first().description
        TodayWeatherModel(date, icon, temp, description)
    }
        .filterIndexed { index, dailyWeatherModel ->
            if (index == 0) {
                currentDay = dailyWeatherModel.date
                true
            } else false
        }
}