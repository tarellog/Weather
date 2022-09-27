package com.example.weather

interface DailyWeatherNavigationProvider {
    fun navigateToCity(): NavCommand
    fun navigateToDialogWindow(): NavCommand
}