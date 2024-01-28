package com.jean.moviesarchitectcoders.movie.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PermissionHandler @Inject constructor(@ApplicationContext private val context: Context) {

    fun checkPermission(permission: Permissions): Boolean {
        return ContextCompat.checkSelfPermission(
            context, permission.toAndroidId()
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun Permissions.toAndroidId(): String {
        return when (this) {
            Permissions.COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
        }
    }

}

enum class Permissions {
    COARSE_LOCATION
}