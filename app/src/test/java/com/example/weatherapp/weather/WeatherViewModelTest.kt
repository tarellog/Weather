package com.example.weatherapp.weather

import com.example.weatherapp.weather.network.weatherrequest.ApiWeatherService
import com.example.weatherapp.weather.network.weatherrequest.RxImmediateSchedulerRule
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private var mockWebServer = MockWebServer()

    lateinit var apiService: ApiWeatherService

    private lateinit var weatherRequest: WeatherService

    private lateinit var weatherLoader: WeatherLoader

    @Before
    fun setUp() {
        weatherRequest = WeatherRequest(apiService)
        weatherLoader = WeatherLoaderImpl(weatherRequest)
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiWeatherService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}