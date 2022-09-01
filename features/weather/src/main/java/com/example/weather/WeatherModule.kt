package com.example.weather

import androidx.lifecycle.ViewModel
import com.example.weather.network.weatherrequest.WeatherRequest
import com.example.weatherapp.weather.network.weatherrequest.ApiWeatherService
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoader
import com.example.weatherapp.weather.usecases.weatherloader.WeatherLoaderImpl
import com.example.weatherapp.weather.usecases.weatherloader.WeatherService
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
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

    @Singleton
    @Provides
    @IntoMap
    @ViewModelKey(DailyWeatherViewModel::class)
    fun getViewModel(loadData: WeatherLoader): ViewModel {
        return DailyWeatherViewModel(loadData)
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)