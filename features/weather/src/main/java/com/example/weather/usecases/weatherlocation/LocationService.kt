package com.example.weather.weather.usecases.weatherlocation

import com.example.weather.weather.usecases.common.WeatherLocation
import io.reactivex.Single

interface LocationService {
    fun getLocation(): Single<WeatherLocation>
}