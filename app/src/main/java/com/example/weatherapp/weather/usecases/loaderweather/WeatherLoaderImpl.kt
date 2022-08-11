package com.example.weatherapp.weather.usecases.loaderweather

import io.reactivex.Single

class WeatherLoaderImpl(private val repository: RemoteRepository) : WeatherLoader {
    override fun loadBasedWeatherData(cityName: String): Single<WeatherResponse> {
        return repository.requestRepository(cityName)
    }
}