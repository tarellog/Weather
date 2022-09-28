package com.example.weatherapp.common

import com.example.weather.DailyWeatherFragmentDirections
import com.example.weather.DailyWeatherNavigationProvider
import com.example.weather.NavCommand
import com.example.weatherapp.AppNavGraphDirections

class DailyWeatherNavigationProviderImpl : DailyWeatherNavigationProvider {
    override fun navigateToCity(): NavCommand {
        val action = AppNavGraphDirections.actionDailyWeatherFragmentToCityFragment()
        return NavCommand(action)
    }

    override fun navigateToDialogWindow(): NavCommand {
        val action =
            DailyWeatherFragmentDirections.actionDailyWeatherFragmentToSearchDialogFragment()
        return NavCommand(action)
    }
}