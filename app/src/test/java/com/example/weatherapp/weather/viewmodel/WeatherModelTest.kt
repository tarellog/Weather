package com.example.weatherapp.weather.viewmodel

import com.example.weatherapp.weather.network.weatherrequest.model.Icon
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import com.example.weatherapp.weather.usecases.weatherloader.TimeWeather
import com.example.weatherapp.weather.usecases.weatherloader.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.Weather
import java.util.*

class WeatherModelTest {
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
}