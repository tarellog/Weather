package com.example.cities

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CityViewModel : ViewModel() {
    private val _citiesList = MutableStateFlow<List<CityElement>>(emptyList())
    val citiesList get() = _citiesList.asStateFlow()

    private val _navigationBack =
        MutableSharedFlow<NavigationByScreen>(0, 1, BufferOverflow.DROP_OLDEST)
    val navigationBack get() = _navigationBack.asSharedFlow()

    private val _navigationByScreenSelectCity =
        MutableSharedFlow<NavigationByScreen>(0, 1, BufferOverflow.DROP_OLDEST)
    val navigationByScreenSelectCity get() = _navigationByScreenSelectCity.asSharedFlow()

    private val _navigationByScreenSelectCityToMap =
        MutableSharedFlow<NavigationByScreen>(0, 1, BufferOverflow.DROP_OLDEST)
    val navigationByScreenSelectCityToMap get() = _navigationByScreenSelectCityToMap.asSharedFlow()

    private val _navigationByScreenWeather =
        MutableSharedFlow<NavigationByScreen>(0, 1, BufferOverflow.DROP_OLDEST)
    val navigationByScreenWeather get() = _navigationByScreenWeather.asSharedFlow()

    private var _messageError = MutableSharedFlow<Int>(0, 1, BufferOverflow.DROP_OLDEST)
    val messageError get() = _messageError.asSharedFlow()

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