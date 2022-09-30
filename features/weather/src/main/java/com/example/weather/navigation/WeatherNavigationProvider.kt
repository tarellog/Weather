package com.example.weather.navigation

interface WeatherNavigationProvider {
    fun createCommandNavigationToScreenCity(): NavCommand
}