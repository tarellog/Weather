package com.example.cities

import android.os.Bundle
import androidx.navigation.NavOptions

data class NavigationByScreen(
    val action: Int,
    val args: Bundle? = null,
    val navOptions: NavOptions? = null
)

data class CityElement(
    val cityName: String,
    val minimumTemperature: Int,
    val maximumTemperature: Int,
    val weatherIcon: String
)