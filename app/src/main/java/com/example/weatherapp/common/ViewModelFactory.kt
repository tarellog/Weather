package com.example.weatherapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.weather.viewmodel.DailyWeatherViewModel
import javax.inject.Inject
import javax.inject.Provider

class FactoryViewModels @Inject constructor(
    viewModelProvider: Provider<DailyWeatherViewModel>,
) : ViewModelProvider.Factory {
    private val provide = mapOf<Class<*>, Provider<out ViewModel>>(
        DailyWeatherViewModel::class.java to viewModelProvider,
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provide[modelClass]?.get() as T
    }
}