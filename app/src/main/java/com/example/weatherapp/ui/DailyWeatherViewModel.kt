package com.example.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.custommodel.DailyWeatherModel
import com.example.weatherapp.data.model.custommodel.TodayWeatherModel
import com.example.weatherapp.data.repository.RemoteRepositoryImpl
import com.example.weatherapp.domain.BasedModel
import com.example.weatherapp.domain.RemoteRepository
import com.example.weatherapp.domain.mapToDisplayModel
import com.example.weatherapp.domain.mapToHeaderDisplayModel

class DailyWeatherViewModel : ViewModel() {

    private val repository: RemoteRepository = RemoteRepositoryImpl()

    private var _dailyWeatherModel = MutableLiveData<List<DailyWeatherModel>>()
    val dailyWeatherModel: LiveData<List<DailyWeatherModel>> get() = _dailyWeatherModel

    private var _todayWeatherModel = MutableLiveData<List<TodayWeatherModel>>()
    val todayWeatherModel: LiveData<List<TodayWeatherModel>> get() = _todayWeatherModel

    fun loadTodayWeatherData() {
        repository.requestRepository()
            .subscribe({ _todayWeatherModel.postValue(it.list.mapToHeaderDisplayModel()) }) {}
    }

    fun loadDailyWeatherData() {
        repository.requestRepository()
            .subscribe({ _dailyWeatherModel.postValue(it.list.mapToDisplayModel()) }) {}
    }


}