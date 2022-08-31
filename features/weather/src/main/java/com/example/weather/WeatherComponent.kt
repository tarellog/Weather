package com.example.weather

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        WeatherModule::class
    ],
    dependencies = [
        WeatherFeatureDependencies::class
    ]
)
@Singleton
interface WeatherComponent : WeatherFeatureApi {

    fun inject(fragment: DailyWeatherFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: WeatherFeatureDependencies): WeatherComponent
    }
}