package com.example.weatherapp.weather

import app.cash.turbine.testIn
import com.example.weatherapp.weather.network.common.ApiWeatherService
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.usecases.common.DailyWeather
import com.example.weatherapp.weather.usecases.common.RxImmediateSchedulerRule
import com.example.weatherapp.weather.usecases.common.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weatherapp.weather.usecases.weatherloader.messageError
import com.example.weatherapp.weather.usecases.weatherloader.weatherActualModel
import com.example.weatherapp.weather.usecases.weatherloader.weatherExpectedModel
import com.example.weatherapp.weather.usecases.weatherlocation.ResponseLocation
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

    @Mock
    lateinit var locations: ResponseLocation

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeather() = runTest {
        Mockito.`when`(apiWeatherService.getApi(weatherActualModel.cityName)).thenReturn(
            Single.just(weatherExpectedModel))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val viewModel = DailyWeatherViewModel(weatherLoader, locations)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

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
        Mockito.`when`(apiWeatherService.getApi(weatherActualModel.cityName))
            .thenReturn(Single.error(Throwable()))

        val weatherRequest = WeatherRequest(apiWeatherService)
        val weatherLoader = WeatherLoaderImpl(weatherRequest)
        val viewModel = DailyWeatherViewModel(weatherLoader, locations)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherActualModel.cityName)

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
