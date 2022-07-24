package com.example.weatherapp.domain

import io.reactivex.rxjava3.core.Single

interface RemoteRepository {
    fun requestRepository(cityName: String): Single<List<BasedModel>>
}