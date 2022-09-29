package com.example.weatherapp.navigation

import com.example.core.common.NavCommand
import com.example.weather.DailyWeatherFragmentDirections
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.AppNavGraphDirections

class WeatherNavigationProviderImpl : WeatherNavigationProvider {
    override fun navigateToCity(): com.example.core.common.NavCommand {
        val action = AppNavGraphDirections.actionDailyWeatherFragmentToCityFragment()
        return com.example.core.common.NavCommand(action.actionId, action.arguments)
    }

    override fun navigateToDialogWindow(): com.example.core.common.NavCommand {
        val action =
            DailyWeatherFragmentDirections.actionDailyWeatherFragmentToSearchDialogFragment()
        return com.example.core.common.NavCommand(action.actionId, action.arguments)
    }
}