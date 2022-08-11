package com.example.weatherapp.weather.network.weatherrequest.model

import com.google.gson.annotations.SerializedName

data class SysModel(
    @SerializedName("pod")
    val pod: String
)
