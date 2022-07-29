package com.example.weatherapp.weather.network.model

import com.google.gson.annotations.SerializedName

data class SysModel(
    @SerializedName("pod")
    val pod: String
)
