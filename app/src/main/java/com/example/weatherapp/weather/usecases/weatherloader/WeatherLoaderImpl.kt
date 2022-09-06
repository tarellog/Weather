package com.example.weatherapp.weather.usecases.weatherloader

import com.example.weatherapp.weather.usecases.common.Weather
import io.reactivex.Single

class WeatherLoaderImpl(private val weatherService: WeatherService) : WeatherLoader {
    override fun getWeather(cityName: String): Single<Weather> {
        return weatherService.getWeather(cityName)
    }
}