package com.example.weatherapp.common

import android.app.Application
import android.content.Context
import com.example.moduleinjector.BaseDependencyHolder
import com.example.moduleinjector.DependencyHolder1
import com.example.weather.common.WeatherComponentHolder
import com.example.weather.common.WeatherFeatureDependencies
import com.example.weatherapp.common.di.AppComponent
import com.example.weatherapp.common.di.DaggerAppComponent
import retrofit2.Retrofit


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent
            .factory()
            .create(this)

        super.onCreate()

        connectModules()
    }

    private fun connectModules() {
        WeatherComponentHolder.dependencyProvider = {
            DependencyHolder1(
                api1 = appComponent
            ) { holder, appComponent ->
                object : WeatherFeatureDependencies {
                    override val retrofit: Retrofit = appComponent.retrofit
                    override val context: Context = appComponent.context
                    override val dependencyHolder: BaseDependencyHolder<WeatherFeatureDependencies> = holder
                }
            }.dependencies
        }
    }
}