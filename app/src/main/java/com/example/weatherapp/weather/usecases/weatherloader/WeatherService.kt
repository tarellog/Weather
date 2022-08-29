package com.example.weatherapp.weather.usecases.weatherloader

import com.example.weatherapp.weather.usecases.common.Weather
import io.reactivex.Single

interface WeatherService {
    fun getWeather(cityName: String): Single<Weather>
}