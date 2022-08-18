package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

class WeatherLocationImpl(private val weatherService: WeatherRequestLocation) : WeatherLocation {
    override fun getWeatherLocation(latitude: Double, longitude: Double): Single<Weather> {
        return weatherService.getWeatherLocation(latitude, longitude)
    }
}