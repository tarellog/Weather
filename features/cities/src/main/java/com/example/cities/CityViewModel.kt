package com.example.cities

import androidx.lifecycle.ViewModel
import com.example.core.flow.MutableSingleEventFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CityViewModel : ViewModel() {
    private val _citiesList = MutableStateFlow<List<CityElement>>(emptyList())
    val citiesList get() = _citiesList.asStateFlow()

    private var _messageError = MutableSingleEventFlow<Int>()
    val messageError get() = _messageError.asSharedFlow()

    fun loadData() {
        _citiesList.tryEmit(
            listOf(
                CityElement(
                    cityName = "Москва",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = "Ясно",
                ),
                CityElement(
                    cityName = "Санкт-Петербург",
                    minimumTemperature = 25,
                    maximumTemperature = 25,
                    weatherIcon = "Ясно",
                )
            )
        )
    }

    fun returnBack() {

    }

    fun changeTheme() {

    }

    fun selectCity() {

    }

    fun addCity() {

    }

    fun selectCityFromMap() {

    }

    fun enterCityThroughDialogWindow() {

    }

    fun removeCity() {

    }
}