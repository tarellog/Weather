package com.example.weather

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.flow.MutableSingleEventFlow
import com.example.weather.adapter.WeatherItem
import com.example.weather.navigation.WeatherRouter
import com.example.weather.usecases.common.Weather
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

    private var _weatherData = MutableStateFlow<List<WeatherItem>>(emptyList())
    val weatherData get() = _weatherData.asStateFlow()

    fun displayDataWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val loadData = loadData.getWeather(cityName)
                getDataWeather(loadData)
            }
            catch (e: Throwable) {
                Log.e("MyTag", "Не получилось получить данные о погоде", e)
                _message.tryEmit(R.string.message)
            }
        }
    }

    private fun getDataWeather(weather: Weather) {
        val weatherList = mutableListOf<WeatherItem>()
        weatherList.add(WeatherItem.HeaderData(weather.headerWeather.first()))
        weather.dailyWeather
            .take(1)
            .forEach { weatherList.addAll(listOf(WeatherItem.WeatherData(it))) }
        weatherList.add(WeatherItem.Title)
        weather.dailyWeather.forEach { weatherList.addAll(listOf(WeatherItem.WeatherToWeek(it))) }
        _weatherData.tryEmit(weatherList)
    }

    @SuppressLint("CheckResult")
    fun getWeatherDataLocation() {
//        locations.getWeatherByLocation()
//            .subscribe({ listWeatherModel ->
//                _weatherData.tryEmit(listOf(listWeatherModel))
//                _dailyWeather.tryEmit(listWeatherModel.dailyWeather)
//            }, {
//                Log.e(
//                    "qwert",
//                    "Не получилось получить погоду по локации",
//                    it
//                )
//                _message.tryEmit(R.string.message)
//            })
    }

    fun navigationToScreenCity() {
        router.openScreenCity()
    }

    fun navigationToScreenDialog() {
        router.openScreenDialog()
    }
}