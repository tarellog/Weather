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

        val expected = weatherResponse.headerWeather
        val actual = viewModel.header.value

        assertEquals(expected, actual)
    }
}