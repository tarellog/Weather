package com.example.weatherapp.common.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.di.WeatherModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [WeatherModule::class])
@Singleton
interface AppComponent {
    val mapModels: Map<Class<*>, ViewModel>
}