package com.example.weatherapp.common

import android.app.Application
import com.example.weatherapp.common.di.DaggerWeatherComponent
import com.example.weatherapp.common.di.WeatherComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerWeatherComponent.create()
    }

    companion object {
        lateinit var appComponent: WeatherComponent
    }

}