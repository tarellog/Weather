package com.example.weatherapp.weather.usecases.weatherlocation

import android.location.Location
import io.reactivex.Single

interface ServiceLocation {
    fun getLocation(): Single<Location>
}