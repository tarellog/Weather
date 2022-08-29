package com.example.weatherapp.weather.usecases.weatherlocation

import android.location.Location
import io.reactivex.Single

interface GetWeatherByLocation {
    fun getLocation(): Single<Location>
}