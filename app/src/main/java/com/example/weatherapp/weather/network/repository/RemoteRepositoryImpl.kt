package com.example.weatherapp.weather.network.repository

import com.example.weatherapp.weather.domain.mapToDisplayModel
import com.example.weatherapp.weather.domain.mapToHeaderDisplayModel
import com.example.weatherapp.weather.network.api.ApiWeatherService
import com.example.weatherapp.weather.usecases.loaderweather.LoaderWeatherImpl
import com.example.weatherapp.weather.usecases.loaderweather.RemoteRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteRepositoryImpl(private val api: ApiWeatherService) : RemoteRepository {
    override fun requestRepository(cityName: String): Single<LoaderWeatherImpl.WeatherResponse> {
        return api.getApi(cityName)
            .map { weatherModel ->
                LoaderWeatherImpl.WeatherResponse(
                    weatherList = buildList {
                        add(weatherModel.list.mapToHeaderDisplayModel())
                        addAll(weatherModel.list.mapToDisplayModel())
                    },
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