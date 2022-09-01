package com.example.weatherapp.common

import android.app.Application
import com.example.moduleinjector.BaseDependencyHolder
import com.example.moduleinjector.DependencyHolder0
import com.example.weather.common.WeatherComponentHolder
import com.example.weather.common.WeatherFeatureDependencies
import com.example.weatherapp.common.di.AppComponent
import com.example.weatherapp.common.di.DaggerAppComponent
import retrofit2.Retrofit


class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        appComponent = DaggerAppComponent.create()
        super.onCreate()

        connectModules()
    }

    private fun connectModules() {
        WeatherComponentHolder.dependencyProvider = {
            DependencyHolder0 { holder: BaseDependencyHolder<WeatherFeatureDependencies> ->
                object : WeatherFeatureDependencies {
                    override val retrofit: Retrofit = appComponent.retrofit
                    override val dependencyHolder = holder
                }
            }.dependencies
        }
    }

}