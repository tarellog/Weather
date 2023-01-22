package com.example.weatherapp.navigation

import com.example.core.navigation.NavCommand
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.AppNavGraphDirections

class WeatherNavigationProviderImpl : WeatherNavigationProvider {
    override fun createCommandNavigationToScreenCity(): NavCommand {
        val action = AppNavGraphDirections.actionDailyWeatherFragmentToCityFragment()
        return NavCommand(action.actionId, action.arguments)
    }

    override fun createCommandNavigationToScreenDialog(): NavCommand {
        val action = AppNavGraphDirections.actionDailyWeatherFragmentToSearchDialogFragment()
        return NavCommand(action.actionId, action.arguments)
    }
}