package com.example.weather.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.m.WeatherComponentHolder

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = WeatherComponentHolder.get().mapModels[modelClass]
        return viewModel as T
    }
}