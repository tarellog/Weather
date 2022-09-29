package com.example.weather.navigation

import com.example.core.common.NavCommand

interface WeatherNavigationProvider {
    fun navigateToCity(): NavCommand
    fun navigateToDialogWindow(): NavCommand
}