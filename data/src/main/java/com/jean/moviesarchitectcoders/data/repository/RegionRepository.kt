package com.jean.moviesarchitectcoders.data.repository

interface RegionRepository {

    suspend fun findLastRegion(): String

}