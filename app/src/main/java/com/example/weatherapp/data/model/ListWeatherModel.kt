package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ListWeatherModel(
    @SerializedName("id")
    val id: Int,
)
