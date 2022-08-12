package com.example.weatherapp.weather.viewmodel

import com.example.weatherapp.weather.DailyWeatherViewModel
import com.example.weatherapp.weather.usecases.weatherloader.Weather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import io.reactivex.Single
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {
    private val CITY_NAME = "Tambov"

    @Mock
    lateinit var weatherLoader: WeatherLoader

    @Test
    fun testLoadBasedWeatherData() {
        val weatherResponse = Weather(
            headerWeather = listOf(),
            dailyWeather = listOf(),
            cityName = "")

        Mockito.`when`(weatherLoader.getWeather(CITY_NAME)).thenReturn(Single.just(weatherResponse))

        val viewModel = DailyWeatherViewModel(weatherLoader)
        viewModel.displayDataWeather(CITY_NAME)

        val expectedHeaderWeather = weatherResponse.headerWeather
        val expectedDailyWeather = weatherResponse.dailyWeather
        val expectedCityWeather = weatherResponse.cityName
        val actualHeader = viewModel.header.value
        val actualDaily = viewModel.dailyWeather.value
        val actualCity = viewModel.city.value

        assertEquals(expectedHeaderWeather, actualHeader)
        assertEquals(expectedDailyWeather, actualDaily)
        assertEquals(expectedCityWeather, actualCity)
    }
}