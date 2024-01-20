package com.jean.moviesarchitectcoders.data.mappers

import android.location.Geocoder
import android.location.Location

fun Location?.toRegion(geocoder: Geocoder): String? {
    val addresses = this?.let {
        geocoder.getFromLocation(latitude, longitude, 1)
    }
    return addresses?.firstOrNull()?.countryCode
}