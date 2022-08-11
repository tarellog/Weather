package com.example.weatherapp.weather.network.repository

import com.example.weatherapp.weather.domain.mapToDisplayModel
import com.example.weatherapp.weather.domain.mapToHeaderDisplayModel
import com.example.weatherapp.weather.network.api.ApiWeatherService
import com.example.weatherapp.weather.usecases.loaderweather.RemoteRepository
import com.example.weatherapp.weather.usecases.loaderweather.WeatherResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteRepositoryImpl(private val api: ApiWeatherService) : RemoteRepository {
    override fun requestRepository(cityName: String): Single<WeatherResponse> {
        return api.getApi(cityName)
            .map { weatherModel ->
                WeatherResponse(
                    headerWeather = buildList {
                        add(weatherModel.list.mapToHeaderDisplayModel())
                    },
                    dailyWeather = buildList {
                        addAll(weatherModel.list.mapToDisplayModel())
                    },
                    cityName = weatherModel.city.name
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}