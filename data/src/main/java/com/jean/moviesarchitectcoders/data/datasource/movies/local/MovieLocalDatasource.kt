package com.jean.moviesarchitectcoders.data.datasource.movies.local

import com.jean.moviesarchitectcoders.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDatasource {

    suspend fun saveMovie(movie: Movie)

    suspend fun saveFavorite(movie: Movie)

    fun getMovies(): Flow<List<Movie>>

    fun getMovieById(movieId: Int): Flow<Movie>

}