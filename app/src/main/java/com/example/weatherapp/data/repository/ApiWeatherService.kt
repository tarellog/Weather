package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiWeatherService {
    @GET("forecast?lat=53&lon=41&appid=296dbfe3af91642e2df4bf1e68064c26")

    fun getApi(): Single<WeatherModel>
}