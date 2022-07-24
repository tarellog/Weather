package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.BasedModel
import com.example.weatherapp.domain.RemoteRepository
import com.example.weatherapp.domain.mapToDisplayModel
import com.example.weatherapp.domain.mapToHeaderDisplayModel
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

    override fun requestRepository(cityName: String): Single<List<BasedModel>> {
        return apiService.getApi(cityName)
            .map { weatherModel ->
                buildList {
                    add(weatherModel.list.mapToHeaderDisplayModel())
                    addAll(weatherModel.list.mapToDisplayModel())
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}