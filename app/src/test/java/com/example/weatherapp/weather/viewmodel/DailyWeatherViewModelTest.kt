package com.example.weatherapp.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

//@RunWith(AndroidJUnit4::class)
@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {

    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: RemoteRepository

    @Mock
    private lateinit var observer: Observer<DailyWeatherViewModel.ViewState>

    @Test
    fun loadBasedWeatherDataTest() {

        val viewModel = DailyWeatherViewModel(repository)

        viewModel.basedModel.observeForever(observer)

        val parameter = RemoteRepositoryImpl.WeatherResponse(
            weatherList = listOf(),
            headerWeather = listOf(),
            dailyWeather = listOf(),
            cityName = "Tambov"
        )
        viewModel.loadBasedWeatherData(parameter.cityName)

        val captor = ArgumentCaptor.forClass(DailyWeatherViewModel.ViewState::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(parameter.cityName, value)
        }
    }

}