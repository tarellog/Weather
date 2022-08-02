package com.example.weatherapp.weather.network.repository

import com.example.weatherapp.weather.domain.BasedModel
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.domain.mapToDisplayModel
import com.example.weatherapp.weather.domain.mapToHeaderDisplayModel
import com.example.weatherapp.weather.network.api.ApiWeatherService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteRepositoryImpl(private val api: ApiWeatherService) : RemoteRepository {
    override fun requestRepository(cityName: String): Single<WeatherResponse> {
        return api.getApi(cityName)
            .map { weatherModel ->
                WeatherResponse(
                    weatherList = buildList {
                        add(weatherModel.list.mapToHeaderDisplayModel())
                        addAll(weatherModel.list.mapToDisplayModel())
                    },
                    cityName = weatherModel.city.name)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    data class WeatherResponse(
        val weatherList: List<BasedModel>,
        val cityName: String
    )
}