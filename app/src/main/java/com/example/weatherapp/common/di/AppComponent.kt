package com.example.weatherapp.common.di

import android.content.Context
import com.example.moduleinjector.BaseFeatureApi
import com.example.weatherapp.common.App
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ContextModule::class
    ]
)
interface AppComponent : BaseFeatureApi {
    val retrofit: Retrofit
    val context: Context

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App
        ): AppComponent
    }
}
