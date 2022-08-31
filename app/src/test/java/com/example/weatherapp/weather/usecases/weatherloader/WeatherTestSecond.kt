package com.example.weatherapp.weather.usecases.weatherloader

import com.example.weatherapp.R
import com.example.weatherapp.weather.network.weatherrequest.model.Icon
import com.example.weatherapp.weather.usecases.common.DailyWeather
import com.example.weatherapp.weather.usecases.common.TimeWeather
import com.example.weatherapp.weather.usecases.common.TodayWeather
import com.example.weatherapp.weather.usecases.common.Weather
import java.util.*

val messageError = R.string.message
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