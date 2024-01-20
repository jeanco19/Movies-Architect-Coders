package com.jean.moviesarchitectcoders.data.datasource.movies.remote

import com.jean.moviesarchitectcoders.domain.models.Movie

interface MovieRemoteDataSource {

    suspend fun getNowPlayingMovies(region: String): List<Movie>

}