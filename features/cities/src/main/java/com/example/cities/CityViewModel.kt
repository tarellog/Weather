package com.example.cities

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CityViewModel : ViewModel() {
    private var _messageError = MutableSharedFlow<Int>(0, 1, BufferOverflow.DROP_OLDEST)
    val messageError get() = _messageError.asSharedFlow()

    private val _citiesList = MutableStateFlow<List<CityElement>>(emptyList())
    val citiesList get() = _citiesList.asStateFlow()

    private val _navigationBack = MutableStateFlow<List<NavigationByScreen>>(emptyList())
    val navigationBack get() = _navigationBack.asStateFlow()

    private val _navigationByScreenSelectCity = MutableStateFlow<List<NavigationByScreen>>(emptyList())
    val navigationByScreenSelectCity get() = _navigationByScreenSelectCity.asStateFlow()

    private val _navigationByScreenSelectCityToMap = MutableStateFlow<List<NavigationByScreen>>(emptyList())
    val navigationByScreenSelectCityToMap get() = _navigationByScreenSelectCityToMap.asStateFlow()

    private val _navigationByScreenWeather = MutableStateFlow<List<NavigationByScreen>>(emptyList())
    val navigationByScreenWeather get() = _navigationByScreenWeather.asStateFlow()

    fun loadData() {

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