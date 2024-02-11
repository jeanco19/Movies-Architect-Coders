package com.jean.moviesarchitectcoders.data.di

import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieRoomDatasource
import com.jean.moviesarchitectcoders.data.datasource.location.LocationPlayServicesDatasource
import com.jean.moviesarchitectcoders.data.datasource.location.LocationDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRetrofitDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun bindMovieRemoteDatasource(
        remoteDatasource: MovieRetrofitDatasource
    ): MovieRemoteDatasource

    @Binds
    abstract fun bindMovieLocalDatasource(
        movieRoomDataSource: MovieRoomDatasource
    ): MovieLocalDatasource

    @Binds
    abstract fun bindLocationRemoteDatasource(
        locationPlayServicesDatasource: LocationPlayServicesDatasource
    ): LocationDatasource

}