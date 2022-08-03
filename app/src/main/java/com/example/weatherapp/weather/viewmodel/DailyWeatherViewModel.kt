package com.example.weatherapp.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel : ViewModel() {
    sealed class ViewState {
        object Empty: ViewState()

        data class Success(
            val weather: RemoteRepositoryImpl.WeatherResponse,
        ) : ViewState()

        object Error : ViewState()
    }

    private val repository: RemoteRepository = RemoteRepositoryImpl()

    private var _basedModel = MutableStateFlow<ViewState>(ViewState.Empty)
    val basedModel get() = _basedModel.asStateFlow()

    fun loadBasedWeatherData(cityName: String) {
        repository.requestRepository(cityName)
            .subscribe({ listWeatherModel ->
                _basedModel.tryEmit(ViewState.Success(listWeatherModel))
            }, {
                _basedModel.tryEmit(ViewState.Error)
            })
    }
}