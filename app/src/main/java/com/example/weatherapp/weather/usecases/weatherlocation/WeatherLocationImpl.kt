package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.weatherloader.Weather
import io.reactivex.Single

class WeatherLocationImpl(
    private val repository: WeatherRequestLocation,
    private val getWeatherByLocation: GetWeatherByLocation
) : WeatherLocation {
    override fun getWeatherByLocation(): Single<Weather> = getWeatherByLocation.getLocation()
        .flatMap {
            repository.getWeatherLocation(it.latitude, it.longitude)
        }
}