package com.example.weatherapp.weather.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.di.RetrofitModule
import com.example.weatherapp.weather.network.api.ApiWeatherService
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import com.example.weatherapp.weather.usecases.loaderweather.LoaderWeather
import com.example.weatherapp.weather.usecases.loaderweather.LoaderWeatherImpl
import com.example.weatherapp.weather.usecases.loaderweather.RemoteRepository
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
    fun provideRepositoryUseCase(service: ApiWeatherService): RemoteRepository =
        RemoteRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideLoadDataUseCase(repository: RemoteRepository): LoaderWeather =
        LoaderWeatherImpl(repository)

    @IntoMap
    @ClassKey(DailyWeatherViewModel::class)
    @Provides
    @Singleton
    fun getViewModel(loadData: LoaderWeather): ViewModel {
        return DailyWeatherViewModel(loadData)
    }
}