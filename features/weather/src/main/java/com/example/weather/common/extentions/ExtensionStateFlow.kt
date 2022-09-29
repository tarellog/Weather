package com.example.weather.common.extentions

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.observe(
    scope: LifecycleCoroutineScope,
    action: suspend (T) -> Unit,
    onError: (Throwable) -> Unit
) = this
    .onEach(action)
    .catch { onError(it) }
    .launchIn(scope)