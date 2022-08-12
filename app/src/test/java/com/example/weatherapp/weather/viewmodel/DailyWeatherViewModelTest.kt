package com.example.weatherapp.weather.viewmodel

import com.example.weatherapp.common.flow.MutableSingleEventFlow
import com.example.weatherapp.weather.DailyWeatherViewModel
import com.example.weatherapp.weather.network.weatherrequest.model.Icon
import com.example.weatherapp.weather.usecases.weatherloader.*
import io.reactivex.Single
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {
    private val CITY_NAME = "Тамбов"

    @Mock
    lateinit var weatherLoader: WeatherLoader

    @Test
    fun testDisplayDataWeather() {
        val weather = Weather(
            headerWeather = listOf(
                TodayWeather(
                    date = Date(),
                    icon = Icon.SUN,
                    temp = 10,
                    description = "Пасмурно"
                )
            ),
            dailyWeather = listOf(
                DailyWeather(
                    date = Date(),
                    minTemp = 5,
                    maxTemp = 16,
                    icon = Icon.SUN,
                    hoursList = listOf(
                        TimeWeather(
                            timeHours = Date(),
                            tempHours = 13,
                            iconHours = Icon.SUN
                        )
                    )
                )
            ),
            cityName = "Москва"
        )
        Mockito.`when`(weatherLoader.getWeather(CITY_NAME)).thenReturn(Single.just(weather))

        val viewModel = DailyWeatherViewModel(weatherLoader)
        viewModel.displayDataWeather(CITY_NAME)

        val expectedHeaderWeather = weather.headerWeather
        val expectedDailyWeather = weather.dailyWeather
        val expectedCityWeather = weather.cityName
        val actualHeader = viewModel.header.value
        val actualDaily = viewModel.dailyWeather.value
        val actualCity = viewModel.city.value

        assertEquals(expectedHeaderWeather, actualHeader)
        assertEquals(expectedDailyWeather, actualDaily)
        assertEquals(expectedCityWeather, actualCity)
    }

    @Test
    fun testStateFlow() = runBlocking {
        val headerWeather = listOf(
            TodayWeather(
                date = Date(),
                icon = Icon.SUN,
                temp = 10,
                description = "Пасмурно"
            )
        )

        val dailyWeather = listOf(
            DailyWeather(
                date = Date(),
                minTemp = 5,
                maxTemp = 16,
                icon = Icon.SUN,
                hoursList = listOf(
                    TimeWeather(
                        timeHours = Date(),
                        tempHours = 13,
                        iconHours = Icon.SUN
                    )
                )
            )
        )

        val cityName = "Ufa"
        val stringMessage = 0

        val _header = MutableStateFlow<List<TodayWeather>>(emptyList())
        val resultsHeader = mutableListOf<List<TodayWeather>>()

        val _daily = MutableStateFlow<List<DailyWeather>>(emptyList())
        val resultsDaily = mutableListOf<List<DailyWeather>>()

        val _city = MutableStateFlow("")
        val resultsCity = mutableListOf("")

        val _message = MutableSingleEventFlow<Int>()
        val resultsMessage = mutableListOf<Int>()

        val job = launch {
            val header = _header.tryEmit(headerWeather)
            val daily = _daily.tryEmit(dailyWeather)
            val city = _city.tryEmit(cityName)
            val message = _message.tryEmit(stringMessage)
            assertEquals(header, resultsHeader)
            assertEquals(daily, resultsDaily)
            assertEquals(city, resultsCity)
            assertEquals(message, resultsMessage)
        }
        job.cancel()
    }
}