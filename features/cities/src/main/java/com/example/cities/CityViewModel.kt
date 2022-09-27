package com.example.cities

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CityViewModel : ViewModel() {
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