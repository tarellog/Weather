package com.example.weatherapp.navigation

import android.app.Activity
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.bottomNavigationView(activity: Activity) {
    this.setOnItemSelectedListener { menu ->
        when (menu.itemId) {
            R.id.main -> {
                activity.navigateFragment(com.example.weather.R.id.weather_nav_graph)
                return@setOnItemSelectedListener true
            }
            R.id.favorite -> {
                activity.navigateFragment(com.example.cities.R.id.city_nav_graph)
                return@setOnItemSelectedListener true
            }
            R.id.search -> {
                activity.navigateFragment(com.example.search.R.id.dialog_nav_graph)
                return@setOnItemSelectedListener true
            }
            else -> { return@setOnItemSelectedListener false }
        }
    }
}

private fun Activity.navigateFragment(@IdRes fragment: Int) {
    findNavController(R.id.container_fragment)
        .navigate(fragment)
}