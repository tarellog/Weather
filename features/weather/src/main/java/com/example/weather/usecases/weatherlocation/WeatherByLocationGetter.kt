package com.example.weather.weather.usecases.weatherlocation

import com.example.weather.usecases.common.Weather
import io.reactivex.Single

interface WeatherByLocationGetter {
    fun getWeatherByLocation(): Single<Weather>
}