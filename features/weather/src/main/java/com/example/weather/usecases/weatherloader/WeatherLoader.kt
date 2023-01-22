package com.example.weather.usecases.weatherloader

import com.example.weather.usecases.common.Weather

interface WeatherLoader {
    suspend fun getWeather(cityName: String): Weather
}