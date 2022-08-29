package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.common.WeatherLocation
import io.reactivex.Single

interface ServiceLocation {
    fun getLocation(): Single<WeatherLocation>
}