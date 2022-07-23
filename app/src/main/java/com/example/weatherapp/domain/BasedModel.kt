package com.example.weatherapp.domain

import java.util.*

sealed class BasedModel {

    data class DailyWeatherModel(
        val date: Date,
        val minTemp: Int,
        val maxTemp: Int,
        val icon: String
    ) : BasedModel()

    data class TodayWeatherModel(
        val date: Date,
        val icon: String,
        val temp: Int,
        val description: String
    ) : BasedModel()

}