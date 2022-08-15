package com.example.weatherapp.weather.usecases.weatherloader

import android.os.Parcelable
import com.example.weatherapp.weather.network.weatherrequest.model.Icon
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Weather(
    val headerWeather: List<TodayWeather>,
    val dailyWeather: List<DailyWeather>,
    val cityName: String
) : Parcelable

@Parcelize
data class TodayWeather(
    val date: Date,
    var icon: Icon,
    val temp: Int,
    val description: String,
) : Parcelable

@Parcelize
data class DailyWeather(
    val date: Date,
    val minTemp: Int,
    val maxTemp: Int,
    val icon: Icon,
    val hoursList: List<TimeWeather>
) : Parcelable

@Parcelize
data class TimeWeather(
    var timeHours: Date,
    val tempHours: Int,
    val iconHours: Icon
) : Parcelable