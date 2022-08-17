package com.example.weatherapp.weather.usecases.weatherloader

import io.reactivex.Single

interface WeatherLoader {
    fun getWeather(cityName: String): Single<Weather>
    fun getWeatherLocation(latitude: Double, longitude: Double): Single<Weather>
}