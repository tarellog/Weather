package com.example.weatherapp.weather.domain

import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {
    fun requestRepository(cityName: String): Single<RemoteRepositoryImpl.WeatherResponse>

}