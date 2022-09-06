package com.example.weatherapp.common

import android.app.Application
import com.example.weatherapp.common.di.AppComponent
import com.example.weatherapp.common.di.ContextModule
import com.example.weatherapp.common.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}