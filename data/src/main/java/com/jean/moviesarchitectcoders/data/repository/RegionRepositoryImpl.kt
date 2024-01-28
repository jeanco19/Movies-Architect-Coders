package com.jean.moviesarchitectcoders.data.repository

import com.jean.moviesarchitectcoders.data.datasource.location.LocationDatasource
import com.jean.moviesarchitectcoders.domain.repository.RegionRepository
import com.jean.moviesarchitectcoders.domain.utils.Constants.DEFAULT_REGION
import javax.inject.Inject

class RegionRepositoryImpl @Inject constructor(
    private val remoteDatasource: LocationDatasource
) : RegionRepository {

    override suspend fun findLastRegion(): String {
        return remoteDatasource.findLastRegion() ?: DEFAULT_REGION
    }

}