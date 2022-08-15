package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

interface WeatherService {
    fun getWeather(cityName: String, latitude: Double, longitude: Double): Single<Weather>
}