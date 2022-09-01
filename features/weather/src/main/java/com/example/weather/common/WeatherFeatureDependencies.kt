package com.example.weather.common

import com.example.moduleinjector.BaseFeatureDependencies
import retrofit2.Retrofit

interface WeatherFeatureDependencies : BaseFeatureDependencies {
    val retrofit: Retrofit
}