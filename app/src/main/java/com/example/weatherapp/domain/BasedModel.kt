package com.example.weatherapp.domain

import com.example.weatherapp.data.model.Icon
import java.util.*

sealed class BasedModel {

    data class DailyWeatherModel(
        val date: Date,
        val minTemp: Int,
        val maxTemp: Int,
        val icon: Icon,
        val hoursList: List<TimeWeatherModel>
    ) : BasedModel()

    data class TodayWeatherModel(
        val date: Date,
        var icon: Icon,
        val temp: Int,
        val description: String,
    ) : BasedModel()

    data class TimeWeatherModel(
        var timeHours: Date,
        val tempHours: Int,
        val iconHours: Icon
    ) : BasedModel()

}