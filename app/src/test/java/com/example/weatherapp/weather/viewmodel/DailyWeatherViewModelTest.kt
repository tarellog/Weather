package com.example.weatherapp.weather.viewmodel

import app.cash.turbine.test
import com.example.weatherapp.weather.DailyWeatherViewModel
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import com.example.weatherapp.weather.usecases.weatherloader.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.Weather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {
    private val headerWeather = weatherHeader
    private val dailyWeather = weatherDaily
    private val cityName = city

    private val weatherModel = Weather(
        headerWeather = headerWeather,
        dailyWeather = dailyWeather,
        cityName = cityName
    )

    private val headerStateFlow = MutableStateFlow<List<TodayWeather>>(emptyList())
    private val dailyStateFlow = MutableStateFlow<List<DailyWeather>>(emptyList())
    private val cityStateFlow = MutableStateFlow("")

    @Mock
    lateinit var weatherLoader: WeatherLoader

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun displayDataWeather() = runTest {
        Mockito.`when`(weatherLoader.getWeather(cityName)).thenReturn(Single.just(weatherModel))

        val viewModel = DailyWeatherViewModel(weatherLoader)
        viewModel.displayDataWeather(cityName)

        viewModel.header.test {
            headerStateFlow.emit(headerWeather)
            assertEquals(awaitItem(), headerWeather)
        }

        viewModel.dailyWeather.test {
            dailyStateFlow.emit(dailyWeather)
            assertEquals(awaitItem(), dailyWeather)
        }

        viewModel.city.test {
            cityStateFlow.emit(cityName)
            assertEquals(awaitItem(), cityName)
        }
    }
}