package com.example.weatherapp.data.model.custommodel

import java.util.*

data class TodayWeatherModel(
    val date: Date,
    val icon: String,
    val temp: Int,
    val description: String
)
