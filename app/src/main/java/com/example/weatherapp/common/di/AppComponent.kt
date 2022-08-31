package com.example.weatherapp.common.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent : WeatherDependencies {

}

interface WeatherDependencies {
    val retrofit: Retrofit
}