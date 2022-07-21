package com.example.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.repository.RemoteRepositoryImpl
import com.example.weatherapp.domain.RemoteRepository

class DailyWeatherViewModel : ViewModel() {

    private val repository: RemoteRepository = RemoteRepositoryImpl()

    private var _listWeatherModel = MutableLiveData<List<ListWeatherModel>>()
    val listWeatherModel: LiveData<List<ListWeatherModel>> get() = _listWeatherModel

    fun loadData() {
        repository.requestRepository()
            .subscribe({ _listWeatherModel.postValue(it.list)
            }){}
    }

}