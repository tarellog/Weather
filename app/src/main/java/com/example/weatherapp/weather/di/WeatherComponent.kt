package com.example.weatherapp.weather.di

import com.example.weatherapp.common.FactoryViewModels
import com.example.weatherapp.common.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        WeatherModule::class,
        RetrofitModule::class
    ]
)
@Singleton

interface WeatherComponent {
    fun viewModelFactory(): FactoryViewModels
}