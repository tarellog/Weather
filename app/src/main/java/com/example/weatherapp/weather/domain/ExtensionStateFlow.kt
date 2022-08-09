package com.example.weatherapp.weather.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.observe(
    scope: CoroutineScope,
    action: suspend (T) -> Unit,
) = this
    .onEach(action)
    .launchIn(scope)