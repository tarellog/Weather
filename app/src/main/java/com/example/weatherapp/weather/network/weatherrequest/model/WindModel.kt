package com.example.weatherapp.weather.network.weatherrequest.model

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double
)
