package com.example.weatherapp.common.di

import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.navigation.WeatherNavigationProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun providesNavigationScreen(): WeatherNavigationProvider =
        WeatherNavigationProviderImpl()
}