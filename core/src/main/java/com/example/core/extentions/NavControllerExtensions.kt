package com.example.core.extentions

import androidx.navigation.NavController
import com.example.core.common.NavCommand

fun NavController.navigate(navigationCommon: NavCommand) {
    navigate(navigationCommon.action, navigationCommon.args, navigationCommon.navOptions)
}