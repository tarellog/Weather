package com.example.weatherapp.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.di.RetrofitModule
import com.example.weatherapp.weather.network.common.ApiWeatherService
import com.example.weatherapp.weather.network.locationrequest.WeatherByLocationGetterImpl
import com.example.weatherapp.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
import com.example.weatherapp.weather.usecases.weatherlocation.LocationDataSource
import com.example.weatherapp.weather.usecases.weatherlocation.LocationDataSourceImpl
import com.example.weatherapp.weather.usecases.weatherlocation.WeatherByLocationGetter
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class WeatherModule {
    @IntoMap
    @ClassKey(DailyWeatherViewModel::class)
    @Provides
    @Singleton
    fun getViewModel(
        loadData: WeatherLoader,
        locations: LocationDataSource,
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
        request: WeatherByLocationGetter,
        getLocation: com.example.weatherapp.weather.usecases.weatherlocation.LocationService
    ): LocationDataSource =
        LocationDataSourceImpl(request, getLocation)

    @Provides
    @Singleton
    fun provideWeatherRequestLocation(service: ApiWeatherService): WeatherByLocationGetter =
        WeatherByLocationGetterImpl(service)

    @Provides
    @Singleton
    fun provideGetWeatherByLocation(context: Context): com.example.weatherapp.weather.usecases.weatherlocation.LocationService =
        com.example.weatherapp.weather.location.LocationServiceImpl(context)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiWeatherService =
        retrofit.create(ApiWeatherService::class.java)
}