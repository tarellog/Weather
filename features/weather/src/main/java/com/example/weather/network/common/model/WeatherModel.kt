package com.example.weatherapp.weather.network.common.model

import com.example.weather.network.common.model.ListWeatherModel
import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<ListWeatherModel>,
    @SerializedName("city")
    val city: CityWeatherModel
)
