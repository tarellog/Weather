package com.example.weatherapp.weather.network.weatherrequest

import com.example.weatherapp.weather.network.weatherrequest.converters.mapToDisplayModel
import com.example.weatherapp.weather.network.weatherrequest.converters.mapToHeaderDisplayModel
import com.example.weatherapp.weather.usecases.common.Weather
import com.example.weatherapp.weather.usecases.common.WeatherLocation
import com.example.weatherapp.weather.usecases.weatherlocation.RequestLocation
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRequestLocation(private val api: ApiWeatherService) : RequestLocation {
    override fun getWeatherLocation(location: WeatherLocation): Single<Weather> {
        return api.getLocation(location.latitude, location.longitude)
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