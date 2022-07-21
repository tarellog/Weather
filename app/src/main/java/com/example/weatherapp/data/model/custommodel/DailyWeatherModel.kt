package com.example.weatherapp.data.model.custommodel

import java.util.*

data class DailyWeatherModel(
    val data: Date,
    val minTemp: Int,
    val maxTemp: Int,
    val icon: String
)
