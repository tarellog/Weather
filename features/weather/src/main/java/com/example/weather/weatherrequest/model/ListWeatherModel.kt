package com.example.weatherapp.weather.network.weatherrequest.model

import com.example.weather.weatherrequest.model.MainWeatherModel
import com.example.weather.weatherrequest.model.WeatherInfoModel
import com.google.gson.annotations.SerializedName

data class ListWeatherModel(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("main")
    val main: MainWeatherModel,
    @SerializedName("weather")
    val weather: List<WeatherInfoModel>,
    @SerializedName("clouds")
    val clouds: CloudsModel,
    @SerializedName("wind")
    val wind: WindModel,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("sys")
    val sys: SysModel,
    @SerializedName("dt_txt")
    val dt_txt: String
)
