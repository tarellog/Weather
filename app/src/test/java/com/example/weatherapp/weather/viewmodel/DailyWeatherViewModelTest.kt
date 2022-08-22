package com.example.weatherapp.weather.viewmodel

import app.cash.turbine.testIn
import com.example.weatherapp.weather.DailyWeatherViewModel
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import com.example.weatherapp.weather.usecases.weatherloader.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {
    @Mock
    lateinit var weatherLoader: WeatherLoader

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun displayDataWeather() = runTest {
        Mockito.`when`(weatherLoader.getWeather(weatherModel.cityName))
            .thenReturn(Single.just(weatherModel))

        val viewModel = DailyWeatherViewModel(weatherLoader)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherModel.cityName)

        assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        assertEquals(weatherModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        assertEquals(weatherModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        assertEquals("", testCityName.awaitItem())
        assertEquals(weatherModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }
}