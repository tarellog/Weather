package com.example.weather.adapter

import com.example.weather.usecases.common.DailyWeather
import com.example.weather.usecases.common.TodayWeather

sealed class WeatherItem {

    data class HeaderData(val headerData: TodayWeather) : WeatherItem()
    data class WeatherData(val weatherData: DailyWeather) : WeatherItem()
    object Title : WeatherItem()
    data class WeatherToWeek(val toWeekData: DailyWeather) : WeatherItem()

}
