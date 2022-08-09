package com.example.weatherapp.common.flow

import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> MutableSingleEventFlow(): MutableSharedFlow<T> {
    return MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1
    )
}