package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class CloudsModel(
    @SerializedName("all")
    val all: Int
)
