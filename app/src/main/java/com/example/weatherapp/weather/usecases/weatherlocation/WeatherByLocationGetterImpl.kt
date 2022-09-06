package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.common.Weather
import io.reactivex.Single

class WeatherByLocationGetterImpl(
    private val dataSource: LocationDataSource,
    private val locationService: LocationService
) : WeatherByLocationGetter {
    override fun getWeatherByLocation(): Single<Weather> = locationService.getLocation()
        .flatMap {
            dataSource.getWeatherLocation(it)
        }
}