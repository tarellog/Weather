package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

class WeatherLoaderImpl(private val weatherService: WeatherService) : WeatherLoader {
    override fun getWeather(cityName: String): Single<Weather> {
        return weatherService.getWeather(cityName)
    }
}