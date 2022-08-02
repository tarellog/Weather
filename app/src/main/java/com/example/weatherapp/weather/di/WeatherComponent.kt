package com.example.weatherapp.weather.di

import com.example.weatherapp.common.FactoryViewModels
import com.example.weatherapp.common.RetrofitModule
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.ui.DailyWeatherFragment
import com.example.weatherapp.weather.viewmodel.DailyWeatherViewModel
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
    fun provideRemoteRepository(): RemoteRepository
    fun inject(fragment: DailyWeatherFragment)
    fun viewModel(): DailyWeatherViewModel
    fun viewModelFactory(): FactoryViewModels
}