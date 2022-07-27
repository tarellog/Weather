package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double
)
