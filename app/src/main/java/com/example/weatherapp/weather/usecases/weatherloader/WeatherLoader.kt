package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

interface WeatherLoader {
    fun getWeather(cityName: String, latitude: Double, longitude: Double): Single<Weather>
}