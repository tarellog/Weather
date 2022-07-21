package com.example.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.model.custommodel.DailyWeatherModel
import com.example.weatherapp.data.repository.RemoteRepositoryImpl
import com.example.weatherapp.domain.RemoteRepository
import com.example.weatherapp.domain.mapToDisplayModel

class DailyWeatherViewModel : ViewModel() {

    private val repository: RemoteRepository = RemoteRepositoryImpl()

    private var _dailyWeatherModel = MutableLiveData<List<DailyWeatherModel>>()
    val dailyWeatherModel: LiveData<List<DailyWeatherModel>> get() = _dailyWeatherModel

    fun loadData() {
        repository.requestRepository()
            .subscribe({ _dailyWeatherModel.postValue(it.list.mapToDisplayModel())
            }){}
    }

}