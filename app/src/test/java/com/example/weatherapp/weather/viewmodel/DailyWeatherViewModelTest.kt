package com.example.weatherapp.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weatherapp.weather.domain.RemoteRepository
import com.example.weatherapp.weather.network.api.ApiWeatherService
import com.example.weatherapp.weather.network.repository.RemoteRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

//@RunWith(AndroidJUnit4::class)
@RunWith(MockitoJUnitRunner::class)
internal class DailyWeatherViewModelTest {

    private val CITY_NAME = ""

    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DailyWeatherViewModel
    private lateinit var repository: RemoteRepository

    @Mock
    private lateinit var observer: Observer<DailyWeatherViewModel.ViewState>

    @Mock
    private lateinit var apiService: ApiWeatherService

    @Before
    fun setUp() {
        repository = RemoteRepositoryImpl(apiService)
        viewModel = DailyWeatherViewModel(repository)
    }

//    @Test
//    fun testNull() {
//        assertNotNull(repository)
//        assertNotNull(viewModel.basedModel)
//        assertNotNull((observer))
//    }

    @Test
    fun testLoadBasedWeatherDataTest() {

        val parameter = RemoteRepositoryImpl.WeatherResponse(
            weatherList = listOf(),
            headerWeather = listOf(),
            dailyWeather = listOf(),
            cityName = ""
        )

        runBlocking {
            Mockito.`when`(repository.requestRepository(apiService.toString()))
                .thenReturn(repository.requestRepository(CITY_NAME))
            viewModel.loadBasedWeatherData(CITY_NAME)
            assertEquals(DailyWeatherViewModel.ViewState.Success(parameter), observer)

        }



//        viewModel.basedModel.observeForever(observer)
//        verify(repository).requestRepository(CITY_NAME)
//        verify(observer).onChanged(DailyWeatherViewModel.ViewState.Success(parameter))
//        viewModel.basedModel.removeObserver(observer)
//        Mockito.`when`(apiService.getApi(Mockito.anyString())).thenReturn(null)
//        assertNotNull(repository.requestRepository(CITY_NAME))
//        Mockito.`when`(repository.requestRepository(Mockito.anyString())).thenReturn(single.map { parameter }.subscribeOn(
//            Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
//        viewModel.loadBasedWeatherData(CITY_NAME)
//        verify(apiService).getApi(Mockito.anyString())
//        verify(observer).onChanged(DailyWeatherViewModel.ViewState.Success(parameter))
//        verify(observer).onChanged(DailyWeatherViewModel.ViewState.Error)
    }
}