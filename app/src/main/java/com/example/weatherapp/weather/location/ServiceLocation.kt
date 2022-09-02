package com.example.weatherapp.weather.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.weatherapp.weather.usecases.common.WeatherLocation
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherServiceLocation
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.Single

class ServiceLocation(context: Context) : WeatherServiceLocation {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getLocation(): Single<WeatherLocation> {
        return Single.create { singleSource ->
            val listener =
                OnSuccessListener<Location> { location ->
                    if (location != null) {
                        singleSource.onSuccess(WeatherLocation(location.latitude, location.longitude))
                    } else {
                        singleSource.onError(IllegalStateException("Can't get location by gps"))
                    }
                }
            val task = fusedLocationClient.lastLocation
            task.addOnSuccessListener(listener)
            task.addOnFailureListener { singleSource.onError(it) }
        }
    }
}