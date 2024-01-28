package com.jean.moviesarchitectcoders.utils

import android.Manifest
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

fun Permissions.toAndroidId(): String {
    return when (this) {
        Permissions.COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
    }
}