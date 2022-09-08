package com.example.weather.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.weather.DailyWeatherViewModel
import com.example.weather.network.locationrequest.LocationDataSourceImpl
import com.example.weather.network.weatherrequest.WeatherRequest
import com.example.weather.weather.usecases.weatherlocation.LocationDataSource
import com.example.weather.weather.usecases.weatherlocation.LocationService
import com.example.weather.weather.usecases.weatherlocation.WeatherByLocationGetter
import com.example.weather.weather.usecases.weatherlocation.WeatherByLocationGetterImpl
import com.example.weatherapp.weather.location.LocationServiceImpl
import com.example.weatherapp.weather.network.weatherrequest.ApiWeatherService
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WeatherModule {
    @Provides
    @IntoMap
    @ViewModelKey(DailyWeatherViewModel::class)
    @Singleton
    fun getViewModel(
        loadData: WeatherLoader,
        locations: WeatherByLocationGetter,
    ): ViewModel {
        return DailyWeatherViewModel(loadData, locations)
    }

    @Provides
    @Singleton
    fun provideLoadDataUseCase(repository: WeatherService): WeatherLoader =
        WeatherLoaderImpl(repository)

    @Provides
    @Singleton
    fun provideRepositoryUseCase(service: ApiWeatherService): WeatherService =
        WeatherRequest(service)

    @Provides
    @Singleton
    fun provideWeatherLocation(
        request: LocationDataSource,
        getLocation: LocationService
    ): WeatherByLocationGetter =
        WeatherByLocationGetterImpl(request, getLocation)

    @Provides
    @Singleton
    fun provideWeatherRequestLocation(service: ApiWeatherService): LocationDataSource =
        LocationDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideGetWeatherByLocation(context: Context): LocationService =
        LocationServiceImpl(context)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiWeatherService =
        retrofit.create(ApiWeatherService::class.java)
}