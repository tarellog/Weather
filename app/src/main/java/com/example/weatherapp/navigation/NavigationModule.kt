package com.example.weatherapp.navigation

import com.example.weather.navigation.NavCommand
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
    fun providesNavigationScreen(): WeatherNavigationProvider =
        WeatherNavigationProviderImpl()

    @Provides
    @Singleton
    fun navigationCommand(): MutableSharedFlow<NavCommand> {
        return MutableSharedFlow(
            replay = 0,
            extraBufferCapacity = 1,
            BufferOverflow.DROP_OLDEST
        )
    }
}