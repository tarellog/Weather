package com.example.weather.weather.usecases.weatherloader

import com.example.weather.network.weatherrequest.model.Icon
import com.example.weather.network.weatherrequest.model.MainWeatherModel
import com.example.weather.network.weatherrequest.model.WeatherInfoModel
import com.example.weatherapp.weather.network.weatherrequest.model.*

val weatherExpectedModel = WeatherModel(
    2,
    4,
    5,
    listOf(
        ListWeatherModel(
            6,
            MainWeatherModel(
                1.2,
                2.1,
                1.9,
                1.3,
                1,
                2,
                3,
                5,
                2.1
            ),
            listOf(WeatherInfoModel(1, "fef", "Пасмурно", Icon.SUN)),
            CloudsModel(2),
            WindModel(2.1, 4, 6.1),
            2,
            4.2,
            SysModel("fsdfsdffsdfsd"),
            "2022-03-15 12:00:00"
        )
    ),
    CityWeatherModel(1,
        "Москва",
        CoordinationModel(4.1, 5.1),
        "rewrwe",
        1,
        1,
        1,
        1)
)