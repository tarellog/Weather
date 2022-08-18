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
    private val weatherModel = Weather(
        headerWeather = weatherHeader,
        dailyWeather = weatherDaily,
        cityName = city
    )

    private val headerStateFlow = MutableStateFlow<List<TodayWeather>>(emptyList())
    private val dailyStateFlow = MutableStateFlow<List<DailyWeather>>(emptyList())
    private val cityStateFlow = MutableStateFlow("")

    @Mock
    lateinit var weatherLoader: WeatherLoader

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun displayDataWeather() = runTest {
        Mockito.`when`(weatherLoader.getWeather(city)).thenReturn(Single.just(weatherModel))

        val viewModel = DailyWeatherViewModel(weatherLoader)
        viewModel.displayDataWeather(city)

        viewModel.header.test {
            headerStateFlow.emit(weatherHeader)
            assertEquals(awaitItem(), weatherHeader)
        }

        viewModel.dailyWeather.test {
            dailyStateFlow.emit(weatherDaily)
            assertEquals(awaitItem(), weatherDaily)
        }

        viewModel.city.test {
            cityStateFlow.emit(city)
            assertEquals(awaitItem(), city)
        }
    }
}