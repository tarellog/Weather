package com.example.weather

import com.example.moduleinjector.BaseFeatureDependencies
import retrofit2.Retrofit

interface WeatherFeatureDependencies : BaseFeatureDependencies {
    val retrofit: Retrofit
}