package com.example.weatherapp.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.di.RetrofitModule
import com.example.weatherapp.weather.location.WeatherGetLocation
import com.example.weatherapp.weather.network.weatherrequest.ApiWeatherService
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequestLocationImpl
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
import com.example.weatherapp.weather.usecases.weatherlocation.GetWeatherByLocation
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherLocation
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherLocationImpl
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherRequestLocation
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
    fun provideGetWeatherByLocation(context: Context): GetWeatherByLocation =
        WeatherGetLocation(context)

    @Provides
    @Singleton
    fun provideWeatherLocation(
        request: WeatherRequestLocation,
        getLocation: GetWeatherByLocation
    ): WeatherLocation =
        WeatherLocationImpl(request, getLocation)

    @IntoMap
    @ClassKey(DailyWeatherViewModel::class)
    @Provides
    @Singleton
    fun getViewModel(
        loadData: WeatherLoader,
        locations: WeatherLocation,
    ): ViewModel {
        return DailyWeatherViewModel(loadData, locations)
    }
}