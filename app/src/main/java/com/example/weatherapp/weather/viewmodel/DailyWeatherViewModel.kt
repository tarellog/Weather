package com.example.weatherapp.weather.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.weatherapp.R
import com.example.weatherapp.common.flow.MutableSingleEventFlow
import com.example.weatherapp.weather.domain.BasedModel
import com.example.weatherapp.weather.domain.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel(
    private val repository: RemoteRepository
) : ViewModel() {

    private var _message = MutableSingleEventFlow<Int>()
    val message get() = _message.asSharedFlow()

    private var _header = MutableStateFlow<List<BasedModel.TodayWeatherModel>>(emptyList())
    val header get() = _header.asStateFlow()

    private var _dailyWeather = MutableStateFlow<List<BasedModel.DailyWeatherModel>>(emptyList())
    val dailyWeather get() = _dailyWeather.asStateFlow()

    private var _city = MutableStateFlow("")
    val city get() = _city.asStateFlow()

    @SuppressLint("CheckResult")
    fun loadBasedWeatherData(cityName: String) {
        repository.requestRepository(cityName)
            .subscribe({ listWeatherModel ->
                _header.tryEmit(listWeatherModel.headerWeather)
                _dailyWeather.tryEmit(listWeatherModel.dailyWeather)
                _city.tryEmit((listWeatherModel.cityName))
            }, {
                _message.tryEmit(R.string.message)
            })
    }
}