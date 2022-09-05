package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.common.Weather
import io.reactivex.Single

class LocationDataSourceImpl(
    private val dataSource: WeatherByLocationGetter,
    private val locationService: LocationService
) : LocationDataSource {
    override fun getWeatherByLocation(): Single<Weather> = locationService.getLocation()
        .flatMap {
            dataSource.getWeatherLocation(it)
        }
}