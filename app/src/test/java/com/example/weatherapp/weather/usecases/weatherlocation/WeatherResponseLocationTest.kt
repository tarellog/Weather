package com.example.weatherapp.weather.usecases.weatherlocation

import com.example.weatherapp.weather.usecases.common.RxImmediateSchedulerRule
import com.example.weatherapp.weather.usecases.common.weatherActualModel
import com.example.weatherapp.weather.usecases.common.weatherLocation
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherResponseLocationTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var locationRequest: WeatherByLocationGetter

    @Mock
    lateinit var locationService: WeatherServiceLocation

    lateinit var weatherResponseLocation: WeatherForReadyLocation

    @Before
    fun setUp() {
        weatherResponseLocation = WeatherReceiverWithReadyLocation(locationRequest, locationService)
    }

    @Test
    fun getWeatherTest() {
        Mockito.`when`(locationRequest.getWeatherLocation(weatherLocation)).thenReturn(
            Single.just(weatherActualModel))

        Mockito.`when`(locationService.getLocation()).thenReturn(
            Single.just(weatherLocation))

        weatherResponseLocation.getWeatherByLocation().test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(weatherActualModel)
            .assertComplete()
    }

    @Test
    fun getWeatherExceptionTest() {
        Mockito.`when`(locationRequest.getWeatherLocation(weatherLocation))
            .thenReturn(Single.error(Throwable()))

        Mockito.`when`(locationService.getLocation()).thenReturn(
            Single.just(weatherLocation))

        weatherResponseLocation.getWeatherByLocation().test()
            .assertValueCount(0)
            .assertNotComplete()
    }
}