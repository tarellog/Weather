package com.example.weather

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.flow.MutableSingleEventFlow
import com.example.weather.navigation.WeatherRouter
import com.example.weather.usecases.common.DailyWeather
import com.example.weather.usecases.common.TodayWeather
import com.example.weather.usecases.weatherloader.WeatherLoader
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DailyWeatherViewModel(
    private val loadData: WeatherLoader,
    private val locations: WeatherByLocationGetter,
    private val router: WeatherRouter
) : ViewModel() {
    private var _message = MutableSingleEventFlow<Int>()
    val message get() = _message.asSharedFlow()

    private var _header = MutableStateFlow<List<TodayWeather>>(emptyList())
    val header get() = _header.asStateFlow()

    private var _dailyWeather = MutableStateFlow<List<DailyWeather>>(emptyList())
    val dailyWeather get() = _dailyWeather.asStateFlow()

    private var _city = MutableStateFlow("")
    val city get() = _city.asStateFlow()

    fun displayDataWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val loadData = loadData.getWeather(cityName)
                _header.tryEmit(loadData.headerWeather)
                _dailyWeather.tryEmit(loadData.dailyWeather)
                _city.tryEmit(loadData.cityName)
            }
            catch (e: Throwable) {
                Log.e(
                    "DailyWeatherViewModel",
                    "Не получилось получить погоду",
                    e
                )
                _message.tryEmit(R.string.message)
            }
        }
    }

    @SuppressLint("CheckResult")
    fun getWeatherDataLocation() {
        locations.getWeatherByLocation()
            .subscribe({ listWeatherModel ->
                _header.tryEmit(listWeatherModel.headerWeather)
                _dailyWeather.tryEmit(listWeatherModel.dailyWeather)
                _city.tryEmit((listWeatherModel.cityName))
            }, {
                Log.e(
                    "qwert",
                    "Не получилось получить погоду по локации",
                    it
                )
                _message.tryEmit(R.string.message)
            })
    }

    fun actionToScreenCity() {
        router.openScreenCity()
    }
}