package com.example.weather.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

class WeatherRouterImpl(
    private val navigationCommand: MutableSharedFlow<NavCommand>,
    private val navigationProvider: WeatherNavigationProvider
) : WeatherRouter {
    override fun openScreenCity() {
            navigationCommand.tryEmit(navigationProvider.navigateByScreen())
    }
}