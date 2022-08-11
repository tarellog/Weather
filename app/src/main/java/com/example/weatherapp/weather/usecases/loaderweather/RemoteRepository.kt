package com.example.weatherapp.weather.usecases.loaderweather

import io.reactivex.Single

interface RemoteRepository {
    fun requestRepository(cityName: String): Single<WeatherResponse>
}