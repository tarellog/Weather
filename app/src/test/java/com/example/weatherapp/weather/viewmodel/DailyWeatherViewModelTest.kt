package com.example.weatherapp.weather.viewmodel

import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import io.reactivex.Single
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {
    private val CITY_NAME = ""

    @Mock
    lateinit var repository: RemoteRepository

    @Test
    fun testLoadBasedWeatherData() {
        val weatherResponse = RemoteRepositoryImpl.WeatherResponse(
            weatherList = listOf(),
            headerWeather = listOf(),
            dailyWeather = listOf(),
            cityName = "")

        Mockito.`when`(repository.requestRepository(CITY_NAME)).thenReturn(Single.just(weatherResponse))

        val viewModel = DailyWeatherViewModel(repository)
        viewModel.loadBasedWeatherData(CITY_NAME)

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