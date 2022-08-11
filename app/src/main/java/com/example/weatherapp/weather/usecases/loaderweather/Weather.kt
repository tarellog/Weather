package com.example.weatherapp.weather.usecases.loaderweather

import com.example.weatherapp.weather.network.model.Icon
import java.util.*

data class WeatherResponse(
    val headerWeather: List<TodayWeatherModel>,
    val dailyWeather: List<DailyWeatherModel>,
    val cityName: String
)

data class DailyWeatherModel(
    val date: Date,
    val minTemp: Int,
    val maxTemp: Int,
    val icon: Icon,
    val hoursList: List<TimeWeatherModel>
)

data class TodayWeatherModel(
    val date: Date,
    var icon: Icon,
    val temp: Int,
    val description: String,
)

data class TimeWeatherModel(
    var timeHours: Date,
    val tempHours: Int,
    val iconHours: Icon
)