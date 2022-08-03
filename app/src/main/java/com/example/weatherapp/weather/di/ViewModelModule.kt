package com.example.weatherapp.weather.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.viewmodel.DailyWeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
    @IntoMap
    @ClassKey(DailyWeatherViewModel::class)
    @Provides
    fun getViewModel(
        repository: RemoteRepository
    ): ViewModel {
        return DailyWeatherViewModel(repository)
    }
}