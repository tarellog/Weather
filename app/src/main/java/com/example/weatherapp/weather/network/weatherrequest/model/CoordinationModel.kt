package com.example.weatherapp.weather.network.weatherrequest.model

import com.google.gson.annotations.SerializedName

data class CoordinationModel(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)