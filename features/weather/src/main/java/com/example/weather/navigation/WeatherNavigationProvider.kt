package com.example.weather.navigation

interface WeatherNavigationProvider {
    fun navigateToCity(): NavCommand
    fun navigateToDialogWindow(): NavCommand
}