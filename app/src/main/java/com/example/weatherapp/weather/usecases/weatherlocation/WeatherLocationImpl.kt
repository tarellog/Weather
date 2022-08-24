package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.weatherloader.Weather
import io.reactivex.Single

class WeatherLocationImpl(private val repository: WeatherRequestLocation ) : WeatherLocation {
    override fun getPermission(lat: Double, lon: Double): Single<Weather> {
        return repository.getWeatherLocation(lat, lon)
    }
}