package com.jean.moviesarchitectcoders.data.di

import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import com.jean.moviesarchitectcoders.data.repository.MoviesRepositoryImpl
import com.jean.moviesarchitectcoders.domain.repository.RegionRepository
import com.jean.moviesarchitectcoders.data.repository.RegionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        movieRepository: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    abstract fun bindRegionRepository(
        regionRepository: RegionRepositoryImpl
    ): RegionRepository

}