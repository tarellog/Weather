package com.example.weatherapp.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.network.api.ApiWeatherService
import com.example.weatherapp.weather.network.model.WeatherModel
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

//@RunWith(AndroidJUnit4::class)
@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {

    private val CITY_NAME = "Тамбов"

    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var repository: RemoteRepository
    private lateinit var viewModel: DailyWeatherViewModel

    @Mock
    private lateinit var observer: Observer<DailyWeatherViewModel.ViewState>

    @Mock
    private lateinit var apiService: ApiWeatherService

    @Mock
    private lateinit var single: Single<WeatherModel>

    @Before
    fun setUp() {
        repository = RemoteRepositoryImpl(apiService)
        viewModel = DailyWeatherViewModel(repository)
        viewModel.basedModel.observeForever(observer)
    }

    @Test
    fun testNull() {
        assertNotNull(repository)
        assertNotNull(viewModel.basedModel)
        assertNotNull((observer))
        assertTrue(viewModel.basedModel.hasObservers())
    }

    @Test
    fun testLoadBasedWeatherDataTest() {
        val parameter = RemoteRepositoryImpl.WeatherResponse(
            weatherList = listOf(),
            headerWeather = listOf(),
            dailyWeather = listOf(),
            cityName = "Tambov"
        )
//        Mockito.`when`(apiService.getApi(Mockito.anyString())).thenReturn(null)
//        assertNotNull(repository.requestRepository(CITY_NAME))
//        Mockito.`when`(repository.requestRepository(Mockito.anyString())).thenReturn(single.map { parameter }.subscribeOn(
//            Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
//        viewModel.loadBasedWeatherData(CITY_NAME)
//        verify(apiService).getApi(Mockito.anyString())
        verify(observer).onChanged(DailyWeatherViewModel.ViewState.Success(parameter))
        verify(observer).onChanged(DailyWeatherViewModel.ViewState.Error)
    }
}