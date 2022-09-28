package com.example.weatherapp.common.di

import com.example.weather.DailyWeatherNavigationProvider
import com.example.weatherapp.common.DailyWeatherNavigationProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun providesNavigationScreen(): DailyWeatherNavigationProvider =
        DailyWeatherNavigationProviderImpl()
}