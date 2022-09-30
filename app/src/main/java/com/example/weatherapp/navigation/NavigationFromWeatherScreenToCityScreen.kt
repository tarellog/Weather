package com.example.weatherapp.navigation

import com.example.weather.navigation.NavCommand
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.AppNavGraphDirections

class NavigationFromWeatherScreenToCityScreen : WeatherNavigationProvider {
    override fun navigateByScreen(): NavCommand {
        val action = AppNavGraphDirections.actionDailyWeatherFragmentToCityFragment()
        return NavCommand(action.actionId, action.arguments)
    }
}