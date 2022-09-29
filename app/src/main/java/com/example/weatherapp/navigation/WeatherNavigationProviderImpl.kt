package com.example.weatherapp.navigation

import com.example.weather.DailyWeatherFragmentDirections
import com.example.weather.navigation.NavCommand
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.AppNavGraphDirections

class WeatherNavigationProviderImpl : WeatherNavigationProvider {
    override fun navigateToCity(): NavCommand {
        val action = AppNavGraphDirections.actionDailyWeatherFragmentToCityFragment()
        return NavCommand(action.actionId, action.arguments)
    }

    override fun navigateToDialogWindow(): NavCommand {
        val action =
            DailyWeatherFragmentDirections.actionDailyWeatherFragmentToSearchDialogFragment()
        return NavCommand(action.actionId, action.arguments)
    }
}