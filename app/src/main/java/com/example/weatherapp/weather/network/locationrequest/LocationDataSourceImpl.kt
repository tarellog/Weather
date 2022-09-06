package com.example.weatherapp.weather.network.locationrequest

import com.example.weatherapp.weather.network.common.ApiWeatherService
import com.example.weatherapp.weather.network.converters.mapToDisplayModel
import com.example.weatherapp.weather.network.converters.mapToHeaderDisplayModel
import com.example.weatherapp.weather.usecases.common.Weather
import com.example.weatherapp.weather.usecases.common.WeatherLocation
import com.example.weatherapp.weather.usecases.weatherlocation.LocationDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocationDataSourceImpl(private val api: ApiWeatherService) : LocationDataSource {
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