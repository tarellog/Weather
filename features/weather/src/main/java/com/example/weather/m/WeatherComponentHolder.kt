package com.example.weather.m

import com.example.moduleinjector.ComponentHolder
import com.example.moduleinjector.ComponentHolderDelegate
import com.example.weather.DaggerWeatherComponent
import com.example.weather.WeatherComponent
import com.example.weather.WeatherFeatureApi
import com.example.weather.WeatherFeatureDependencies

object WeatherComponentHolder : ComponentHolder<WeatherFeatureApi, WeatherFeatureDependencies> {

    private val componentHolderDelegate =
        ComponentHolderDelegate<WeatherFeatureApi, WeatherFeatureDependencies, WeatherComponent> { dependencies ->
            DaggerWeatherComponent
                .factory()
                .create(dependencies)
        }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): WeatherFeatureApi =
        componentHolderDelegate.get()

    internal fun getComponentImpl() =
        componentHolderDelegate.getComponentImpl()

}