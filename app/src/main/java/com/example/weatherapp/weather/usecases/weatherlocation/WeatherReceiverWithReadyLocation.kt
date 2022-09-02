package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.common.Weather
import io.reactivex.Single

class WeatherReceiverWithReadyLocation(
    private val repository: WeatherByLocationGetter,
    private val getWeatherByLocation: WeatherServiceLocation
) : WeatherForReadyLocation {
    override fun getWeatherByLocation(): Single<Weather> = getWeatherByLocation.getLocation()
        .flatMap {
            repository.getWeatherLocation(it)
        }
}