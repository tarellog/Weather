package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ListWeatherModel(
    @SerializedName("id")
    val id: Int,
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
    val pop: Int,
    @SerializedName("sys")
    val sys: SysModel,
    @SerializedName("dt_txt")
    val dt_txt: String
)
