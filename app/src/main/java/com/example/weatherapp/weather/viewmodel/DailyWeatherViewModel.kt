package com.example.weatherapp.weather.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.weatherapp.R
import com.example.weatherapp.weather.domain.BasedModel
import com.example.weatherapp.weather.domain.RemoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel(
    private val repository: RemoteRepository
) : ViewModel() {

    private var _message = MutableSharedFlow<Int>(0, 1)
    val message get() = _message.asSharedFlow()

    private var _headerModel = MutableStateFlow<List<BasedModel.TodayWeatherModel>>(emptyList())
    val headerModel get() = _headerModel.asStateFlow()

    private var _dailyModel = MutableStateFlow<List<BasedModel.DailyWeatherModel>>(emptyList())
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
                _message.tryEmit(R.string.message)
            })
    }
}