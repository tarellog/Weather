package com.example.weatherapp.weather.network.weatherrequest

import com.example.weatherapp.weather.network.weatherrequest.converters.mapToDisplayModel
import com.example.weatherapp.weather.network.weatherrequest.converters.mapToHeaderDisplayModel
import com.example.weatherapp.weather.usecases.weatherloader.Weather
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherRequestLocation
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRequestLocationImpl(private val api: ApiWeatherService) : WeatherRequestLocation {
    override fun getWeatherLocation(latitude: Double, longitude: Double): Single<Weather> {
        return api.getLocation(latitude, longitude)
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