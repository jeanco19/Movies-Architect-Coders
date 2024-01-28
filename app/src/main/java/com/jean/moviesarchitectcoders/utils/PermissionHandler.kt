package com.jean.moviesarchitectcoders.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext

class PermissionHandler(@ApplicationContext private val context: Context) {

    fun validatePermission(
        permission: Permissions,
        permissionGranted: () -> Unit,
        requirePermission: () -> Unit
    ) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                permission.toAndroidId()
            ) == PackageManager.PERMISSION_GRANTED -> {
                permissionGranted()
            }

            else -> {
                requirePermission()
            }
        }
    }

}

enum class Permissions {
    COARSE_LOCATION
}