package com.example.weatherapp.weather.network.weatherrequest

import com.example.weatherapp.weather.network.weatherrequest.converters.mapToDisplayModel
import com.example.weatherapp.weather.network.weatherrequest.converters.mapToHeaderDisplayModel
import com.example.weatherapp.weather.usecases.weatherloader.Weather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRequest(private val api: ApiWeatherService) : WeatherService {
    override fun getWeather(cityName: String): Single<Weather> {
        return api.getApi(cityName)
            .map { weatherModel ->
                Weather(
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