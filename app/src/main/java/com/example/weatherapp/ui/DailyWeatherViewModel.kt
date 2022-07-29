package com.example.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.RemoteRepository
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl

class DailyWeatherViewModel : ViewModel() {

    sealed class ViewState {
        data class Success(
            val weather: RemoteRepositoryImpl.WeatherResponse,
        ) : ViewState()

        object Error : ViewState()
    }

    private val repository: RemoteRepository = RemoteRepositoryImpl()

    private var _basedModel = MutableLiveData<ViewState>()
    val basedModel: LiveData<ViewState> get() = _basedModel

    fun loadBasedWeatherData(cityName: String) {
        repository.requestRepository(cityName)
            .subscribe({ listWeatherModel ->
                _basedModel.postValue(ViewState.Success(listWeatherModel))
            }, {
                _basedModel.postValue(ViewState.Error)
            })
    }

}