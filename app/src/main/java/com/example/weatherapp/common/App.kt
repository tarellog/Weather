package com.example.weatherapp.common

import android.app.Application
import com.example.moduleinjector.BaseDependencyHolder
import com.example.moduleinjector.DependencyHolder0
import com.example.weather.WeatherFeatureDependencies
import com.example.weather.m.WeatherComponentHolder


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        connectModules()
    }

    private fun connectModules() {
        WeatherComponentHolder.dependencyProvider = {
            DependencyHolder0 { holder: BaseDependencyHolder<WeatherFeatureDependencies> ->
                object : WeatherFeatureDependencies {
                    override val dependencyHolder = holder
                }
            }.dependencies
        }
    }
}