package com.example.weatherapp.weather.viewmodel

import com.example.weatherapp.weather.DailyWeatherViewModel
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import com.example.weatherapp.weather.usecases.weatherloader.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {
    private val CITY_NAME = "Тамбов"

    @Mock
    lateinit var weatherLoader: WeatherLoader

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeather() = runTest {
        val weather = WeatherModelTest().weather

        Mockito.`when`(weatherLoader.getWeather(CITY_NAME)).thenReturn(Single.just(weather))

        val viewModel = DailyWeatherViewModel(weatherLoader)
        viewModel.displayDataWeather(CITY_NAME)

        val _header = MutableStateFlow<List<TodayWeather>>(emptyList())
        val _daily = MutableStateFlow<List<DailyWeather>>(emptyList())
        val _city = MutableStateFlow("")

        val job = launch {
            val header = _header.tryEmit(weather.headerWeather)
            val daily = _daily.tryEmit(weather.dailyWeather)
            val city = _city.tryEmit(weather.cityName)
            assertEquals(weather.headerWeather, header)
            assertEquals(weather.dailyWeather, daily)
            assertEquals(weather.cityName, city)
        }
        job.cancel()
    }
}