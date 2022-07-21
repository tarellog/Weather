package com.example.weatherapp.data.model.custommodel

import java.util.*

data class DailyModel(
    val data: Date,
    val minTemp: Int,
    val maxTemp: Int,
    val icon: String
)
