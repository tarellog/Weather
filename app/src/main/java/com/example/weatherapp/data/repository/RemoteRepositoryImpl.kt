package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.model.ListWeatherModel
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.domain.RemoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    override fun requestRepository(): Single<WeatherModel> {
        return apiService.getApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}