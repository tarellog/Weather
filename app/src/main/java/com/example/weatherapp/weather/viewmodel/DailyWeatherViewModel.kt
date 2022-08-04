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
    sealed class ViewState {
        object Empty: ViewState()

        data class Header(
            val today: List<BasedModel.TodayWeatherModel>
        ) : ViewState()

        data class Success(
            val daily: List<BasedModel.DailyWeatherModel>,
        ) : ViewState()

        data class City(
            val city: String
        ) : ViewState()

        object Error : ViewState()
    }

    private var _empty = MutableStateFlow<ViewState>(ViewState.Empty)
    val empty get() = _empty.asStateFlow()

    private var _error = MutableSharedFlow<ViewState.Error>(0, 1)
    val error get() = _error.asSharedFlow()

    private var _headerModel = MutableStateFlow<ViewState>(ViewState.Empty)
    val headerModel get() = _headerModel.asStateFlow()

    private var _dailyModel = MutableStateFlow<ViewState>(ViewState.Empty)
    val dailyModel get() = _dailyModel.asStateFlow()

    private var _city = MutableStateFlow<ViewState>(ViewState.Empty)
    val city get() = _city.asStateFlow()

    @SuppressLint("CheckResult")
    fun loadBasedWeatherData(cityName: String) {
        repository.requestRepository(cityName)
            .subscribe({ listWeatherModel ->
                _empty.tryEmit(ViewState.Empty)
                _headerModel.tryEmit(ViewState.Header(listWeatherModel.headerWeather))
                _dailyModel.tryEmit(ViewState.Success(listWeatherModel.dailyWeather))
                _city.tryEmit(ViewState.City(listWeatherModel.cityName))
            }, {
                _error.tryEmit(ViewState.Error)
            })
    }
}