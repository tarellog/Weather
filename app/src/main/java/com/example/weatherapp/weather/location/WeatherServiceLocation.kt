package com.example.weatherapp.weather.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.example.weatherapp.weather.usecases.weatherlocation.ServiceLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import io.reactivex.Single

class WeatherServiceLocation(private val context: Context) : ServiceLocation {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun getLocation(): Single<Location> {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val task = fusedLocationClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return Single.create { singleSource ->
                val listener =
                    OnSuccessListener<Location> { location ->
                        if (location != null) {
                            singleSource.onSuccess(location)
                        } else {
                            singleSource.onError(IllegalStateException("Can't get location by gps"))
                        }
                    }
                task.addOnSuccessListener(listener)
                task.addOnFailureListener { singleSource.onError(it) }
            }
        } else return Single.error(IllegalStateException("Don't have permission on getting location"))
    }
}