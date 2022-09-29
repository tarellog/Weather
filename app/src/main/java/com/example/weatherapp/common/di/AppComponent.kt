package com.example.weatherapp.common.di

import android.content.Context
import com.example.moduleinjector.BaseFeatureApi
import com.example.weather.navigation.WeatherNavigationProvider
import com.example.weatherapp.common.App
import com.example.weatherapp.common.MainActivity
import com.example.weatherapp.navigation.NavigationModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ContextModule::class,
        NavigationModule::class
    ]
)
interface AppComponent : BaseFeatureApi {
    val retrofit: Retrofit
    val context: Context
    fun navigationRouter(): WeatherNavigationProvider
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App
        ): AppComponent
    }
}
