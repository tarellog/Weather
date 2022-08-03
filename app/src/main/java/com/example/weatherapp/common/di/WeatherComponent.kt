package com.example.weatherapp.common.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.di.RepositoryModule
import com.example.weatherapp.weather.di.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RepositoryModule::class,
        RetrofitModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface WeatherComponent {
    val mapModels: Map<Class<*>, ViewModel>
}