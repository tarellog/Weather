package com.example.weather.core

import androidx.navigation.NavController
import com.example.weather.NavCommand

fun NavController.navigate(navigationCommon: NavCommand) {
    navigate(navigationCommon.action, navigationCommon.args, navigationCommon.navOptions)
}