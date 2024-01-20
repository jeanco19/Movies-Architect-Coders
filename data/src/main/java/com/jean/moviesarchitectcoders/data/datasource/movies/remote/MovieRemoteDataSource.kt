package com.jean.moviesarchitectcoders.data.datasource.movies.remote

import com.jean.moviesarchitectcoders.data.models.Movie

interface MovieRemoteDataSource {

    suspend fun getNowPlayingMovies(region: String): List<Movie>

}