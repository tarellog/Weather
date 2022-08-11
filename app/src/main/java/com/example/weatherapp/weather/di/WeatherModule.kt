package com.example.weatherapp.weather.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.di.RetrofitModule
import com.example.weatherapp.weather.network.api.ApiWeatherService
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import com.example.weatherapp.weather.usecase.LoadDataImplUseCase
import com.example.weatherapp.weather.usecase.LoadDataUseCase
import com.example.weatherapp.weather.usecase.RepositoryUseCase
import com.example.weatherapp.weather.viewmodel.DailyWeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class WeatherModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiWeatherService =
        retrofit.create(ApiWeatherService::class.java)

    @Provides
    @Singleton
    fun provideRepositoryUseCase(service: ApiWeatherService): RepositoryUseCase =
        RemoteRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideLoadDataUseCase(repository: RepositoryUseCase): LoadDataUseCase =
        LoadDataImplUseCase(repository)

    @IntoMap
    @ClassKey(DailyWeatherViewModel::class)
    @Provides
    @Singleton
    fun getViewModel(loadData: LoadDataUseCase): ViewModel {
        return DailyWeatherViewModel(loadData)
    }
}