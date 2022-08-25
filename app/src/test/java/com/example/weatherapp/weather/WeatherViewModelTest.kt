package com.example.weatherapp.weather

import app.cash.turbine.testIn
import com.example.weatherapp.weather.network.weatherrequest.ApiWeatherService
import com.example.weatherapp.weather.network.weatherrequest.RxImmediateSchedulerRule
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.usecases.weatherloader.*
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var apiWeatherService: ApiWeatherService

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeather() = runTest {
        Mockito.`when`(apiWeatherService.getApi(weatherModel.cityName)).thenReturn(
            Single.just(weatherExpectedModel))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val viewModel = DailyWeatherViewModel(weatherLoader)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherModel.cityName)

        Assertions.assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        Assertions.assertEquals(weatherActualModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        Assertions.assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        Assertions.assertEquals(weatherActualModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        Assertions.assertEquals("", testCityName.awaitItem())
        Assertions.assertEquals(weatherActualModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeatherWithException() = runTest {
        Mockito.`when`(apiWeatherService.getApi(weatherModel.cityName))
            .thenReturn(Single.error(Throwable()))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val viewModel = DailyWeatherViewModel(weatherLoader)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherModel.cityName)

        Assertions.assertEquals(messageError, testMessage.awaitItem())
        testMessage.cancel()

        Assertions.assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        Assertions.assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        Assertions.assertEquals("", testCityName.awaitItem())
        testCityName.cancel()
    }
}
