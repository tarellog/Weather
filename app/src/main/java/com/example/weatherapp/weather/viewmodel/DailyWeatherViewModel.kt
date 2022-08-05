package com.example.weatherapp.weather.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel(
    private val repository: RemoteRepository
) : ViewModel() {
    sealed class ViewState {
        object Empty: ViewState()

        data class Success(
            val weather: RemoteRepositoryImpl.WeatherResponse,
        ) : ViewState()

        object Error : ViewState()
    }

    private var _basedModel = MutableStateFlow<ViewState>(ViewState.Empty)
    val basedModel get() = _basedModel.asStateFlow()

    private var _error = MutableSharedFlow<ViewState.Error>(0, 1)
    val error get() = _error.asSharedFlow()


    @SuppressLint("CheckResult")
    fun loadBasedWeatherData(cityName: String) {
        repository.requestRepository(cityName)
            .subscribe({ listWeatherModel ->
                _basedModel.tryEmit(ViewState.Success(listWeatherModel))
            }, {
                _error.tryEmit(ViewState.Error)
            })
    }
}