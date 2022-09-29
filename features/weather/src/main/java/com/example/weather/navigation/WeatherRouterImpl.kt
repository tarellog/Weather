package com.example.weather.navigation

import com.example.weather.BuildConfig
import kotlinx.coroutines.flow.MutableSharedFlow

class WeatherRouterImpl(
    private val navigationCommand: MutableSharedFlow<NavCommand>,
    private val navigationProvider: WeatherNavigationProvider
) : WeatherRouter {
    override fun openScreenCity() {
        if (BuildConfig.ENABLING_SCREEN_CITY == true) {
            navigationCommand.tryEmit(navigationProvider.navigateToCity())
        } else {
            navigationCommand.tryEmit(navigationProvider.navigateToDialogWindow())
        }
    }
}