package com.example.weatherapp.navigation

import com.example.weather.navigation.WeatherNavigationProvider
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