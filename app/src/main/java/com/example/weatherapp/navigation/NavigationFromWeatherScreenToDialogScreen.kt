package com.example.weatherapp.navigation

import com.example.weather.DailyWeatherFragmentDirections
import com.example.weather.navigation.NavCommand
import com.example.weather.navigation.WeatherNavigationProvider

class NavigationFromWeatherScreenToDialogScreen : WeatherNavigationProvider{
    override fun createCommandNavigationToScreenCity(): NavCommand {
        val action = DailyWeatherFragmentDirections.actionDailyWeatherFragmentToSearchDialogFragment()
        return NavCommand(action.actionId, action.arguments)
    }
}