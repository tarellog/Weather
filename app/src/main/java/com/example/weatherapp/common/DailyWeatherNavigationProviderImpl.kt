package com.example.weatherapp.common

import com.example.weather.DailyWeatherNavigationProvider
import com.example.weather.NavCommand
import com.example.weatherapp.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyWeatherNavigationProviderImpl @Inject constructor(): DailyWeatherNavigationProvider {
    override fun navigateToCity(): NavCommand {
        return NavCommand(R.id.action_dailyWeatherFragment_to_cityFragment)
    }
}