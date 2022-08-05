package com.example.weatherapp.weather.network.repository

import com.example.weatherapp.weather.domain.BasedModel
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.domain.mapToDisplayModel
import com.example.weatherapp.weather.domain.mapToHeaderDisplayModel
import com.example.weatherapp.weather.network.api.ApiWeatherService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteRepositoryImpl(private val api: ApiWeatherService) : RemoteRepository {
    override fun requestRepository(cityName: String): Single<WeatherResponse> {
        return api.getApi(cityName)
            .map { weatherModel ->
                WeatherResponse(
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
                    cityName = weatherModel.city.name)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    data class WeatherResponse(
        val weatherList: List<BasedModel>,
        val headerWeather: List<BasedModel.TodayWeatherModel>,
        val dailyWeather: List<BasedModel.DailyWeatherModel>,
        val cityName: String
    )

}