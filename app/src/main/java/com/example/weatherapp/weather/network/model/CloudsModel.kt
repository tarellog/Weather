package com.example.weatherapp.weather.network.model

import com.google.gson.annotations.SerializedName

data class CloudsModel(
    @SerializedName("all")
    val all: Int
)
