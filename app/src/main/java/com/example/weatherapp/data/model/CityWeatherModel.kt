package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class CityWeatherModel(
    @SerializedName("dt")
    val dt: Int,
)
