package com.example.weatherapp.weather.network.weatherrequest

import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
import com.example.weatherapp.weather.usecases.weatherloader.weatherActualModel
import com.example.weatherapp.weather.usecases.weatherloader.weatherExpectedModel
import com.example.weatherapp.weather.weatherModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherRequestTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var apiWeatherService: ApiWeatherService

    lateinit var weatherRequest: WeatherService

    @Before
    fun setUp() {
        weatherRequest = WeatherRequest(apiWeatherService)
    }

    @Test
    fun getWeatherTest() {
        Mockito.`when`(apiWeatherService.getApi(weatherModel.cityName)).thenReturn(
            Single.just(weatherExpectedModel))

        weatherRequest.getWeather(weatherModel.cityName).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherExceptionTest() {
        Mockito.`when`(apiWeatherService.getApi(weatherModel.cityName))
            .thenReturn(Single.error(Throwable()))

        weatherRequest.getWeather(weatherModel.cityName).test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}