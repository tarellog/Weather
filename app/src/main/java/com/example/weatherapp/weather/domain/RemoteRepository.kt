package com.example.weatherapp.weather.domain

import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import io.reactivex.Single

interface RemoteRepository {

    fun requestRepository(cityName: String): Single<RemoteRepositoryImpl.WeatherResponse>
}