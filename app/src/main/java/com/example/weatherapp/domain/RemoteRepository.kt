package com.example.weatherapp.domain

import com.example.weatherapp.data.model.WeatherModel
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {
    fun requestRepository(): Single<WeatherModel>
}