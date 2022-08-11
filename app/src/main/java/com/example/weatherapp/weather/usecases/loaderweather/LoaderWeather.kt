package com.example.weatherapp.weather.usecases.loaderweather

import io.reactivex.Single

interface LoaderWeather {
    fun loadBasedWeatherData(cityName: String): Single<LoaderWeatherImpl.WeatherResponse>
}