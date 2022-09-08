package com.example.weather.network.common.model

import com.example.weatherapp.weather.network.common.model.CloudsModel
import com.example.weatherapp.weather.network.common.model.SysModel
import com.example.weatherapp.weather.network.common.model.WindModel
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
