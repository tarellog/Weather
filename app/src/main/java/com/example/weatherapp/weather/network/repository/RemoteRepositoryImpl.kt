package com.example.weatherapp.weather.network.repository

import com.example.weatherapp.domain.BasedModel
import com.example.weatherapp.domain.RemoteRepository
import com.example.weatherapp.domain.mapToDisplayModel
import com.example.weatherapp.domain.mapToHeaderDisplayModel
import com.example.weatherapp.weather.network.api.ApiWeatherService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepositoryImpl : RemoteRepository {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiService: ApiWeatherService = retrofit.create(ApiWeatherService::class.java)

    override fun requestRepository(cityName: String): Single<WeatherResponse> {
        return apiService.getApi(cityName)
            .map { weatherModel ->
                WeatherResponse(
                    weatherList = buildList {
                        add(weatherModel.list.mapToHeaderDisplayModel())
                        addAll(weatherModel.list.mapToDisplayModel())
                    },
                    cityName = weatherModel.city.name)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    data class WeatherResponse(
        val weatherList: List<BasedModel>,
        val cityName: String
    )
}