package com.example.weather.common.extentions

import androidx.navigation.NavController
import com.example.weather.navigation.NavCommand

fun NavController.navigate(navigationCommon: NavCommand) {
    navigate(navigationCommon.action, navigationCommon.args, navigationCommon.navOptions)
}