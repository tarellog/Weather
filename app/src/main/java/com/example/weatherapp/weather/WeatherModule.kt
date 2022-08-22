package com.example.weatherapp.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.di.RetrofitModule
import com.example.weatherapp.weather.network.weatherrequest.ApiWeatherService
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequestLocation
import com.example.weatherapp.weather.usecases.weatherbylocation.WeatherLocation
import com.example.weatherapp.weather.usecases.weatherbylocation.WeatherLocationImpl
import com.example.weatherapp.weather.usecases.weatherbylocation.WeatherServiceLocation
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
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
    fun provideWeatherRequestLocation(service: ApiWeatherService): WeatherServiceLocation =
        WeatherRequestLocation(service)

    @Provides
    @Singleton
    fun provideWeatherLocation(context:Context): WeatherLocation =
        WeatherLocationImpl(context)

    @IntoMap
    @ClassKey(DailyWeatherViewModel::class)
    @Provides
    @Singleton
    fun getViewModel(loadData: WeatherLoader, weatherLocation: WeatherLocation): ViewModel {
        return DailyWeatherViewModel(loadData, weatherLocation)
    }
}