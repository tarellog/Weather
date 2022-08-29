package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.common.Weather
import io.reactivex.Single

interface ResponseLocation {
    fun getWeatherByLocation(): Single<Weather>
}