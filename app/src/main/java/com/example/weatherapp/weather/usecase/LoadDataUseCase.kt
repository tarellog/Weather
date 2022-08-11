package com.example.weatherapp.weather.usecase

import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import io.reactivex.Single

interface LoadDataUseCase {
    fun loadBasedWeatherData(cityName: String): Single<RemoteRepositoryImpl.WeatherResponse>
}