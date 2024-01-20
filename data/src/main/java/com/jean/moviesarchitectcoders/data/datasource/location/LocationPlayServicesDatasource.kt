package com.jean.moviesarchitectcoders.data.datasource.location

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.jean.moviesarchitectcoders.data.mappers.toRegion
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationPlayServicesDatasource @Inject constructor(
    application: Application
) : LocationDatasource {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application)

    @SuppressLint("MissingPermission")
    override suspend fun findLastRegion(): String? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnCompleteListener { location ->
                continuation.resume(location.result.toRegion(geocoder))
            }
        }
    }

}