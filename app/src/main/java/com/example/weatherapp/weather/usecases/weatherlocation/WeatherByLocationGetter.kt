package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.common.Weather
import com.example.weatherapp.weather.usecases.common.WeatherLocation
import io.reactivex.Single

interface WeatherByLocationGetter {
    fun getWeatherLocation(location: WeatherLocation): Single<Weather>
}