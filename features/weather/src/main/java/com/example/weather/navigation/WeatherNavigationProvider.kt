package com.example.weather.navigation

interface WeatherNavigationProvider {
    fun navigateByScreen(): NavCommand
}