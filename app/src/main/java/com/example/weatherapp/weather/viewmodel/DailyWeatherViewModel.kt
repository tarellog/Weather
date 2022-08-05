package com.example.weatherapp.weather.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.domain.BasedModel
import com.example.weatherapp.weather.domain.RemoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel(
    private val repository: RemoteRepository
) : ViewModel() {

    object Error

    private var _error = MutableSharedFlow<Error>(0, 1)
    val error get() = _error.asSharedFlow()

    private var _headerModel = MutableStateFlow<List<BasedModel.TodayWeatherModel>>(listOf())
    val headerModel get() = _headerModel.asStateFlow()

    private var _dailyModel = MutableStateFlow<List<BasedModel.DailyWeatherModel>>(listOf())
    val dailyModel get() = _dailyModel.asStateFlow()

    private var _city = MutableStateFlow("")
    val city get() = _city.asStateFlow()


    @SuppressLint("CheckResult")
    fun loadBasedWeatherData(cityName: String) {
        repository.requestRepository(cityName)
            .subscribe({ listWeatherModel ->
                _headerModel.tryEmit(listWeatherModel.headerWeather)
                _dailyModel.tryEmit(listWeatherModel.dailyWeather)
                _city.tryEmit((listWeatherModel.cityName))
            }, {
                _error.tryEmit(Error)
            })
    }
}