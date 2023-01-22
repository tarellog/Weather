package com.example.weatherapp.navigation

import com.example.core.navigation.NavCommand
import com.example.weather.navigation.WeatherNavigationProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun providesNavigationScreenCity(): WeatherNavigationProvider =
        WeatherNavigationProviderImpl()

    @Provides
    @Singleton
    fun navigationCommand(): MutableSharedFlow<NavCommand> =
        MutableSharedFlow(
            replay = 0,
            extraBufferCapacity = 1,
            BufferOverflow.DROP_OLDEST
        )
}