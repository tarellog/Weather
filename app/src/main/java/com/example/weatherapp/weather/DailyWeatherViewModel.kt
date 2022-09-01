package com.example.weatherapp.weather

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.R
import com.example.weatherapp.common.flow.MutableSingleEventFlow
import com.example.weatherapp.weather.usecases.common.DailyWeather
import com.example.weatherapp.weather.usecases.common.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherlocation.ResponseLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel(
    private val loadData: WeatherLoader,
    private val locations: ResponseLocation
) : ViewModel() {
    private var _message = MutableSingleEventFlow<Int>()
    val message get() = _message.asSharedFlow()

    private var _header = MutableStateFlow<List<TodayWeather>>(emptyList())
    val header get() = _header.asStateFlow()

    private var _dailyWeather = MutableStateFlow<List<DailyWeather>>(emptyList())
    val dailyWeather get() = _dailyWeather.asStateFlow()

    private var _city = MutableStateFlow("")
    val city get() = _city.asStateFlow()

    @SuppressLint("CheckResult")
    fun displayDataWeather(cityName: String) {
        loadData.getWeather(cityName)
            .subscribe({ listWeatherModel ->
                _header.tryEmit(listWeatherModel.headerWeather)
                _dailyWeather.tryEmit(listWeatherModel.dailyWeather)
                _city.tryEmit((listWeatherModel.cityName))
            }, {
                Log.e(
                    "DailyWeatherViewModel",
                    "Не получилось получить погоду по локации",
                    it
                )
                _message.tryEmit(R.string.message)
            })
    }

    @SuppressLint("CheckResult")
    fun getWeatherDataLocation() {
        locations.getWeatherByLocation()
            .subscribe({ listWeatherModel ->
                _header.tryEmit(listWeatherModel.headerWeather)
                _dailyWeather.tryEmit(listWeatherModel.dailyWeather)
                _city.tryEmit((listWeatherModel.cityName))
            }, {
                _message.tryEmit(R.string.message)
            })
    }
}