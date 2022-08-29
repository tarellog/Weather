package com.example.weatherapp.weather.usecases.weatherloader

import com.example.weatherapp.weather.usecases.common.Weather
import io.reactivex.Single

interface WeatherLoader {
    fun getWeather(cityName: String): Single<Weather>
}