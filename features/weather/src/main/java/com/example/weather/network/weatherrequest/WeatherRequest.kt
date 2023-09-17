package com.example.weather.network.weatherrequest

import com.example.weather.network.common.ApiWeatherService
import com.example.weather.network.common.converters.mapToDisplayModel
import com.example.weather.network.common.converters.mapToHeaderDisplayModel
import com.example.weather.usecases.common.Weather
import com.example.weather.usecases.weatherloader.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRequest(private val api: ApiWeatherService) : WeatherService {
    override suspend fun getWeather(cityName: String): Weather {
        return withContext(Dispatchers.Default) {
            val remoteData = api.getApi(cityName)
            Weather(
                headerWeather = buildList {
                    add(remoteData.mapToHeaderDisplayModel())
                },
                dailyWeather = buildList {
                    addAll(remoteData.list.mapToDisplayModel())
                },
            )
        }
    }
}