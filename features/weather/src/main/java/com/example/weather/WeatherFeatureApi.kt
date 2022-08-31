package com.example.weather

import androidx.lifecycle.ViewModel
import com.example.moduleinjector.BaseFeatureApi

interface WeatherFeatureApi : BaseFeatureApi {
    val mapModels: Map<Class<*>, ViewModel>
}