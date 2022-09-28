package com.example.weather

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weather.common.extentions.MutableSingleEventFlow
import com.example.weather.usecases.common.DailyWeather
import com.example.weather.usecases.common.TodayWeather
import com.example.weather.usecases.weatherloader.WeatherLoader
import com.example.weather.usecases.weatherlocation.WeatherByLocationGetter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel(
    private val loadData: WeatherLoader,
    private val locations: WeatherByLocationGetter,
    private val navigationProvider: DailyWeatherNavigationProvider
) : ViewModel() {

    private var _navigationCommand = MutableSingleEventFlow<NavCommand>()
    val navigationCommand get() = _navigationCommand.asSharedFlow()

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

    private fun actionNavigationCity() {
        _navigationCommand.tryEmit(navigationProvider.navigateToCity())
    }

    private fun actionNavigationDialogWindow() {
        _navigationCommand.tryEmit(navigationProvider.navigateToDialogWindow())
    }

    fun includeBuildConfig() {
        if (BuildConfig.ENABLING_SCREEN_CITY == true) {
            actionNavigationCity()
        } else {
            actionNavigationDialogWindow()
        }
    }
}