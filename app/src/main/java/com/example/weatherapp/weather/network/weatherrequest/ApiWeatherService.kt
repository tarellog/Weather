package com.example.weatherapp.weather.network.weatherrequest

import com.example.weatherapp.weather.network.weatherrequest.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherService {

    companion object {
        const val CITY_NAME = "q"
    }

    @GET("forecast?lang=ru&units=metric&appid=296dbfe3af91642e2df4bf1e68064c26")
    fun getApi(@Query(CITY_NAME) cityName: String): Single<WeatherModel>

}