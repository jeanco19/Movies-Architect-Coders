package com.jean.moviesarchitectcoders.data.datasource.movies.remote

import com.jean.moviesarchitectcoders.domain.models.Movie

interface MovieRemoteDatasource {

    suspend fun getNowPlayingMovies(region: String): List<Movie>

}