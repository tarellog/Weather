package com.example.weatherapp.weather.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.example.weatherapp.weather.usecases.common.WeatherLocation
import com.example.weatherapp.weather.usecases.weatherlocation.ServiceLocation
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.Single

class WeatherServiceLocation(private val context: Context) : ServiceLocation {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun getLocation(): Single<WeatherLocation> {
        return if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getWeatherLocation()
        } else Single.error(IllegalStateException("Don't have permission on getting location"))
    }

    @SuppressLint("MissingPermission")
    private fun getWeatherLocation(): Single<WeatherLocation> {
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