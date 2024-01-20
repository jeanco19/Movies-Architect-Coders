package com.jean.moviesarchitectcoders.data.datasource.location

interface LocationDatasource {

    suspend fun findLastRegion(): String?

}