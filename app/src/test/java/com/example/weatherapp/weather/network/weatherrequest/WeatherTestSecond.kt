package com.example.weatherapp.weather.network.weatherrequest

import com.example.weatherapp.weather.network.weatherrequest.model.Icon
import com.example.weatherapp.weather.usecases.weatherloader.DailyWeather
import com.example.weatherapp.weather.usecases.weatherloader.TimeWeather
import com.example.weatherapp.weather.usecases.weatherloader.TodayWeather
import com.example.weatherapp.weather.usecases.weatherloader.Weather
import java.util.*

val weatherActualModel = Weather(
    headerWeather = listOf(
        TodayWeather(
            date = Date(122, 2,15,0,0,0),
            icon = Icon.SUN,
            temp = 1,
            description = "Пасмурно"
        )
    ),
    dailyWeather = listOf(
        DailyWeather(
            date = Date(122, 2,15,0,0,0),
            minTemp = 1,
            maxTemp = 1,
            icon = Icon.SUN,
            hoursList = listOf(
                TimeWeather(
                    timeHours = Date(122, 2,15,12,0,0),
                    tempHours = 1,
                    iconHours = Icon.SUN
                )
            )
        )
    ),
    cityName = "Москва"
)