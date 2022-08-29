package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.weatherloader.Weather
import io.reactivex.Single

class WeatherResponseLocation(
    private val repository: RequestLocation,
    private val getWeatherByLocation: ServiceLocation
) : ResponseLocation {
    override fun getWeatherByLocation(): Single<Weather> = getWeatherByLocation.getLocation()
        .flatMap {
            repository.getWeatherLocation(it.latitude, it.longitude)
        }
}