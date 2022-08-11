package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

interface WeatherService {
    fun getWeather(cityName: String): Single<Weather>
}