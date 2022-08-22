package com.example.weatherapp.weather.usecases.weatherbylocation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class WeatherLocationImpl(
    private val context: Context
) : WeatherLocation {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun getLocationPermission() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val task = fusedLocationClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                Activity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 101
            )
            return
        }
        task.addOnSuccessListener{ location ->
            location.latitude
            location.longitude
        }
    }
}

