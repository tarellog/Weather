package com.example.weatherapp.common.di

import android.content.Context
import com.example.weatherapp.common.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(var app: App) {
    @Provides
    @Singleton
    fun provideApp(): App = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext
}