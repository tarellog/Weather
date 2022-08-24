package com.example.weatherapp.weather.usecases.weatherlocation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class WeatherPermissionLocation(private val context: Context) : WeatherPermission {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun getPermission(listener: (Location) -> Unit) {
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
        task.addOnSuccessListener(listener)
    }
}