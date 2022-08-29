package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.weatherloader.Weather
import io.reactivex.Single

interface RequestLocation {
    fun getWeatherLocation(latitude: Double, longitude: Double): Single<Weather>
}