package com.example.weatherapp.weather.network.locationrequest

import com.example.weatherapp.weather.network.common.ApiWeatherService
import com.example.weatherapp.weather.usecases.common.RxImmediateSchedulerRule
import com.example.weatherapp.weather.usecases.common.weatherActualModel
import com.example.weatherapp.weather.usecases.common.weatherExpectedModel
import com.example.weatherapp.weather.usecases.common.weatherLocation
import com.example.weatherapp.weather.usecases.weatherlocation.RequestLocation
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationRequestTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var apiWeatherService: ApiWeatherService

    lateinit var weatherRequest: RequestLocation

    @Before
    fun setUp() {
        weatherRequest = WeatherRequestLocation(apiWeatherService)
    }

    @Test
    fun getWeatherLocationTest() {
        Mockito.`when`(apiWeatherService.getLocation(weatherLocation.latitude, weatherLocation.longitude)).thenReturn(
            Single.just(weatherExpectedModel))

        weatherRequest.getWeatherLocation(weatherLocation).test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherLocationExceptionTest() {
        Mockito.`when`(apiWeatherService.getLocation(weatherLocation.latitude, weatherLocation.longitude))
            .thenReturn(Single.error(Throwable()))

        weatherRequest.getWeatherLocation(weatherLocation).test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}