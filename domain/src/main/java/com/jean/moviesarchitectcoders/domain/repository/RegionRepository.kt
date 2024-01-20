package com.jean.moviesarchitectcoders.domain.repository

interface RegionRepository {

    suspend fun findLastRegion(): String

}