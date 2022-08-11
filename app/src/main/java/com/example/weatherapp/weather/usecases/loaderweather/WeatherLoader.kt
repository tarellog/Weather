package com.example.weatherapp.weather.usecases.loaderweather

import io.reactivex.Single

interface WeatherLoader {
    fun loadBasedWeatherData(cityName: String): Single<WeatherResponse>
}