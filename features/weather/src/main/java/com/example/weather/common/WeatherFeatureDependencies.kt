package com.example.weather.common

import android.content.Context
import com.example.moduleinjector.BaseFeatureDependencies
import com.example.weather.DailyWeatherNavigationProvider
import retrofit2.Retrofit

interface WeatherFeatureDependencies : BaseFeatureDependencies {
    val retrofit: Retrofit
    val context: Context
    val navigation: DailyWeatherNavigationProvider
}