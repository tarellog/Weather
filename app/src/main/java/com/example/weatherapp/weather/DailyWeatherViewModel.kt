package com.example.weatherapp.weather

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.weatherapp.R
import com.example.weatherapp.common.flow.MutableSingleEventFlow
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import com.example.weatherapp.weather.usecases.weatherloader.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherLocation
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherPermission
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherViewModel(
    private val loadData: WeatherLoader,
    private val locations: WeatherLocation,
    private val locationPermission: WeatherPermission
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
        cityName.let {
            loadData.getWeather(cityName)
                .subscribe({ listWeatherModel ->
                    _header.tryEmit(listWeatherModel.headerWeather)
                    _dailyWeather.tryEmit(listWeatherModel.dailyWeather)
                    _city.tryEmit((listWeatherModel.cityName))
                }, {
                    _message.tryEmit(R.string.message)
                })
        }
    }

    private fun displayDataLocation(location: Location?) {
        location?.let {
            locations.getPermission(location.latitude, location.longitude)
                .subscribe({ listWeatherModel ->
                    _header.tryEmit(listWeatherModel.headerWeather)
                    _dailyWeather.tryEmit(listWeatherModel.dailyWeather)
                    _city.tryEmit((listWeatherModel.cityName))
                }, {
                    _message.tryEmit(R.string.message)
                })
        }
    }

    fun permissionLocation() {
        locationPermission.getPermission { displayDataLocation(it) }
    }
}