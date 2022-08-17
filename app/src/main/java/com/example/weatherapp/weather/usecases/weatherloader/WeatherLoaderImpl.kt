package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

class WeatherLoaderImpl(private val weatherService: WeatherService) : WeatherLoader {
    override fun getWeather(cityName: String): Single<Weather> {
        return weatherService.getWeather(cityName)
    }

    override fun getWeatherLocation(latitude: Double, longitude: Double): Single<Weather> {
        return weatherService.getWeatherLocation(latitude, longitude)
    }
}