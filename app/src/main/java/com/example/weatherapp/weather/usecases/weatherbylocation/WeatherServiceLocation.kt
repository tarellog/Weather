package com.example.weatherapp.weather.usecases.weatherbylocation

import com.example.weatherapp.weather.usecases.weatherloader.Weather
import io.reactivex.Single

interface WeatherServiceLocation {
    fun getServiceWeatherLocation(latitude: Double, longitude: Double): Single<Weather>
}