package com.jean.moviesarchitectcoders.data.di

import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieRoomDataSource
import com.jean.moviesarchitectcoders.data.datasource.location.LocationPlayServicesDatasource
import com.jean.moviesarchitectcoders.data.datasource.location.LocationDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDataSource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MoviesRetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun bindMovieRemoteDatasource(
        remoteDatasource: MoviesRetrofitDataSource
    ): MovieRemoteDataSource

    @Binds
    abstract fun bindMovieLocalDatasource(
        movieRoomDataSource: MovieRoomDataSource
    ): MovieLocalDatasource

    @Binds
    abstract fun bindLocationRemoteDatasource(
        locationPlayServicesDatasource: LocationPlayServicesDatasource
    ): LocationDatasource

}