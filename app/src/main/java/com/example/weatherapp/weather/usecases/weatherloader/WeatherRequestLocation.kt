package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

interface WeatherRequestLocation {
    fun getWeatherLocation(latitude: Double, longitude: Double): Single<Weather>
}