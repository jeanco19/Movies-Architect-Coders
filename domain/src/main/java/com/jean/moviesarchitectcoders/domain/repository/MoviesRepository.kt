package com.jean.moviesarchitectcoders.domain.repository

import com.jean.moviesarchitectcoders.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(region: String): Flow<Result<List<Movie>>>
    fun getFavorites(): Flow<Result<List<Movie>>>

    fun getMovieById(movieId: Int): Flow<Result<Movie>>

    suspend fun saveFavorite(movie: Movie)

}