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

    @Mock
    lateinit var weatherRequest: WeatherService

    @Mock
    lateinit var weatherLoader: WeatherLoader

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeather() = runTest {
        Mockito.`when`(weatherLoader.getWeather(weatherModel.cityName))
            .thenReturn(Single.just(weatherModel))

        val viewModel = DailyWeatherViewModel(weatherLoader)

        val testMessage = viewModel.message.testIn(this)
        val testHeaderWeather = viewModel.header.testIn(this)
        val testDailyWeather = viewModel.dailyWeather.testIn(this)
        val testCityName = viewModel.city.testIn(this)

        viewModel.displayDataWeather(weatherModel.cityName)

        Assertions.assertEquals(emptyList<TodayWeather>(), testHeaderWeather.awaitItem())
        Assertions.assertEquals(weatherModel.headerWeather, testHeaderWeather.awaitItem())
        testHeaderWeather.cancel()

        Assertions.assertEquals(emptyList<DailyWeather>(), testDailyWeather.awaitItem())
        Assertions.assertEquals(weatherModel.dailyWeather, testDailyWeather.awaitItem())
        testDailyWeather.cancel()

        Assertions.assertEquals("", testCityName.awaitItem())
        Assertions.assertEquals(weatherModel.cityName, testCityName.awaitItem())
        testCityName.cancel()

        testMessage.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDisplayDataWeatherWithException() = runTest {
        Mockito.`when`(weatherLoader.getWeather(weatherModel.cityName))
            .thenReturn(Single.error(Throwable()))

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

    @Test
    fun getWeatherRequestTest() {
        weatherRequest = WeatherRequest(apiWeatherService)
        Mockito.`when`(apiWeatherService.getApi(weatherModel.cityName)).thenReturn(
            Single.just(weatherExpectedModel))

        weatherRequest.getWeather(weatherModel.cityName).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherRequestExceptionTest() {
        weatherRequest = WeatherRequest(apiWeatherService)
        Mockito.`when`(apiWeatherService.getApi(weatherModel.cityName))
            .thenReturn(Single.error(Throwable()))

        weatherRequest.getWeather(weatherModel.cityName).test()
            .assertValueCount(0)
            .assertNotComplete()
    }

    @Test
    fun getWeatherTest() {
        weatherLoader = WeatherLoaderImpl(weatherRequest)
        Mockito.`when`(weatherRequest.getWeather(weatherModel.cityName)).thenReturn(
            Single.just(weatherActualModel)
        )

        weatherLoader.getWeather(weatherModel.cityName).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherExceptionTest() {
        weatherLoader = WeatherLoaderImpl(weatherRequest)
        Mockito.`when`(weatherRequest.getWeather(weatherModel.cityName))
            .thenReturn(Single.error(Throwable()))

        weatherLoader.getWeather(weatherModel.cityName).test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}
