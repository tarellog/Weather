package com.example.weather.usecases.weatherloader

import com.example.weather.usecases.common.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherLoaderImpl(private val weatherService: WeatherService) : WeatherLoader {
    override suspend fun getWeather(cityName: String): Weather {
        return withContext(Dispatchers.Default) { weatherService.getWeather(cityName) }
    }
}