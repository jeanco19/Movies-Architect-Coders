package com.jean.moviesarchitectcoders.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext

class PermissionHandler(@ApplicationContext private val context: Context) {

    fun validatePermission(
        permissionGranted: () -> Unit,
        requirePermission: () -> Unit
    ) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                permissionGranted()
            }

            else -> {
                requirePermission()
            }
        }
    }

}