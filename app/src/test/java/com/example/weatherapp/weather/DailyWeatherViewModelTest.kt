package com.example.weatherapp.weather

import app.cash.turbine.testIn
import com.example.weather.DailyWeatherViewModel
import com.example.weatherapp.weather.usecases.weatherloader.*
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
    fun testDisplayDataWeather() = runTest {
        Mockito.`when`(weatherLoader.getWeather(weatherActualModel.cityName))
            .thenReturn(Single.just(weatherActualModel))

        val viewModel = DailyWeatherViewModel(weatherLoader)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

        assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        assertEquals(weatherActualModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        assertEquals(weatherActualModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        assertEquals("", testCityName.awaitItem())
        assertEquals(weatherActualModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeatherWithException() = runTest {
        Mockito.`when`(weatherLoader.getWeather(weatherActualModel.cityName))
            .thenReturn(Single.error(Throwable()))

        val viewModel = DailyWeatherViewModel(weatherLoader)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

        assertEquals(messageError, testMessage.awaitItem())
        testMessage.cancel()

        assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        assertEquals("", testCityName.awaitItem())
        testCityName.cancel()
    }
}