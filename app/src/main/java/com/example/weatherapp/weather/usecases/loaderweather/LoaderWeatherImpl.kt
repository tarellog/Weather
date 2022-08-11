package com.example.weatherapp.weather.usecases.loaderweather

import com.example.weatherapp.weather.domain.BasedModel
import io.reactivex.Single

class LoaderWeatherImpl(private val repository: RemoteRepository) : LoaderWeather {
    override fun loadBasedWeatherData(cityName: String): Single<WeatherResponse> {
        return repository.requestRepository(cityName)
    }
    data class WeatherResponse(
        val weatherList: List<BasedModel>,
        val headerWeather: List<BasedModel.TodayWeatherModel>,
        val dailyWeather: List<BasedModel.DailyWeatherModel>,
        val cityName: String
    )
}