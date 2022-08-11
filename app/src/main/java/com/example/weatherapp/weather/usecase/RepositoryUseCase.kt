package com.example.weatherapp.weather.usecase

import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import io.reactivex.Single

interface RepositoryUseCase {
    fun requestRepository(cityName: String): Single<RemoteRepositoryImpl.WeatherResponse>
}