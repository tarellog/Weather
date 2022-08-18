package com.example.weatherapp.weather

import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.di.RetrofitModule
import com.example.weatherapp.weather.network.weatherrequest.ApiWeatherService
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequestLocationImpl
import com.example.weatherapp.weather.usecases.weatherloader.*
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
    fun provideRepositoryUseCase(service: ApiWeatherService): WeatherService =
        WeatherRequest(service)

    @Provides
    @Singleton
    fun provideLoadDataUseCase(repository: WeatherService): WeatherLoader =
        WeatherLoaderImpl(repository)

    @Provides
    @Singleton
    fun provideWeatherRequestLocation(service: ApiWeatherService): WeatherRequestLocation =
        WeatherRequestLocationImpl(service)

    @Provides
    @Singleton
    fun provideWeatherLocation(repository: WeatherRequestLocation): WeatherLocation =
        WeatherLocationImpl(repository)

    @IntoMap
    @ClassKey(DailyWeatherViewModel::class)
    @Provides
    @Singleton
    fun getViewModel(loadData: WeatherLoader, weatherLocation: WeatherLocation): ViewModel {
        return DailyWeatherViewModel(loadData, weatherLocation)
    }
}