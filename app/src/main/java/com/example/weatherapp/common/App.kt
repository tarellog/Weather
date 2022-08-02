package com.example.weatherapp.common

import android.app.Application
import com.example.weatherapp.weather.di.DaggerWeatherComponent
import com.example.weatherapp.weather.di.WeatherComponent
import com.example.weatherapp.weather.di.WeatherModule

class App : Application() {
    lateinit var appComponent: WeatherComponent

    override fun onCreate() {
        instance = this
        super.onCreate()
        appComponent = DaggerWeatherComponent
            .builder()
            .weatherModule(WeatherModule())
            .build()
    }

    companion object {
        var instance: App = App()

        fun getApp(): App {
            return instance
        }
    }
}