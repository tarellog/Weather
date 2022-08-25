package com.example.weatherapp.weather.usecases.weatherlocation

import android.location.Location
import com.example.weatherapp.weather.usecases.weatherloader.Weather
import io.reactivex.Single

interface WeatherLocation {
    fun getLocation(lat: Double, lon: Double): Single<Weather>
    fun getPermission(listener: (Location) -> Unit)
}