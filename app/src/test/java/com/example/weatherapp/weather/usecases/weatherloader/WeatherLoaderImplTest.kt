package com.example.weatherapp.weather.usecases.weatherloader

import com.example.weatherapp.weather.network.weatherrequest.RxImmediateSchedulerRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherLoaderImplTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var weatherRequest: WeatherService

    lateinit var weatherLoader: WeatherLoader

    @Before
    fun setUp() {
        weatherLoader = WeatherLoaderImpl(weatherRequest)
    }

    @Test
    fun getWeatherTest() {
        Mockito.`when`(weatherRequest.getWeather(weatherActualModel.cityName)).thenReturn(
            Single.just(weatherActualModel))

        weatherLoader.getWeather(weatherActualModel.cityName).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherExceptionTest() {
        Mockito.`when`(weatherRequest.getWeather(weatherActualModel.cityName))
            .thenReturn(Single.error(Throwable()))

        weatherLoader.getWeather(weatherActualModel.cityName).test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}