package com.example.weatherapp.weather.usecase

import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import io.reactivex.Single

class LoadDataImplUseCase(private val repository: RepositoryUseCase) : LoadDataUseCase {
    override fun loadBasedWeatherData(cityName: String): Single<RemoteRepositoryImpl.WeatherResponse> {
        return repository.requestRepository(cityName)
    }
}