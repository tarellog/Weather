package com.example.weatherapp.weather.usecases.weatherlocation

import android.location.Location

interface WeatherPermission {
    fun getPermission(listener: (Location) -> Unit)
}