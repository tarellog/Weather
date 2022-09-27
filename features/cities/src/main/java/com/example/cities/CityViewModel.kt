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