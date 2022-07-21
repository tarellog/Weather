package com.example.weatherapp.domain

import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.model.custommodel.DailyWeatherModel
import java.text.SimpleDateFormat
import java.util.*

fun List<ListWeatherModel>.mapToDisplayModel(): List<DailyWeatherModel> {
    var currentDay = Date()
    return this
        .map {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(it.dt_txt)
        val minTemp = it.main.temp_min.toInt()
        val maxTemp = it.main.temp_max.toInt()
        val icon = it.weather.first().icon
        DailyWeatherModel(date, minTemp, maxTemp, icon)
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